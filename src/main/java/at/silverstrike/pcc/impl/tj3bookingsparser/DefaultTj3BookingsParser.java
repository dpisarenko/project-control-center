/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.tj3bookingsparser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2Bookings;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParser;
import at.silverstrike.pcc.impl.tj3bookingsparser.grammar.BookingsLexer;
import at.silverstrike.pcc.impl.tj3bookingsparser.grammar.BookingsParser;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultTj3BookingsParser implements Tj3BookingsParser {
	private List<BookingTuple> bookings;
	private InputStream inputStream;
	private Injector injector = null;

	public DefaultTj3BookingsParser() {

	}

	public List<BookingTuple> getBookings() {
		return bookings;
	}

	public void setInputStream(final InputStream aInputStream) {
		this.inputStream = aInputStream;
	}

	@Override
	public void run() throws PccException {
		try {
			final BookingsFile bookingsFile = inputStreamToBookingsFile();
			this.bookings = bookingsFileToBookings(bookingsFile);
		} catch (final IOException exception) {
			throw new PccException(exception);
		} catch (final RecognitionException exception) {
			throw new PccException(exception);
		} catch (final NumberFormatException exception) {
			throw new PccException(exception);
		} catch (final ParseException exception) {
			throw new PccException(exception);
		}
	}

	private List<BookingTuple> bookingsFileToBookings(
			final BookingsFile aBookingsFile) throws ParseException {
		final BookingsFile2BookingsFactory factory = this.injector
				.getInstance(BookingsFile2BookingsFactory.class);
		final BookingsFile2Bookings converter = factory.create();

		converter.setBookingsFile(aBookingsFile);
		converter.setPersistence(this.injector.getInstance(Persistence.class));
		converter.run();

		return converter.getTuples();
	}

	private BookingsFile inputStreamToBookingsFile() throws IOException,
			RecognitionException {
		final BookingsLexer lexer = new BookingsLexer(new ANTLRInputStream(
				this.inputStream));
		final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		final BookingsParser parser = new BookingsParser(tokenStream);
		parser.bookingsFile();
		final BookingsFile returnValue = parser.getBookingsFile();
		return returnValue;
	}

	@Override
	public void setInjector(final Injector aInjector) {
		this.injector = aInjector;
	}
}
