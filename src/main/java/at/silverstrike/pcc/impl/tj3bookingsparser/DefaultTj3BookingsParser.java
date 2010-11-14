/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
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
    private Injector injector;

    public DefaultTj3BookingsParser() {

    }

    public List<BookingTuple> getBookings() {
        return bookings;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
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
            final BookingsFile aBookingsFile) throws NumberFormatException,
            ParseException {
        final BookingsFile2BookingsFactory factory =
                this.injector.getInstance(BookingsFile2BookingsFactory.class);
        final BookingsFile2Bookings converter = factory.create();

        converter.setBookingsFile(aBookingsFile);
        converter.setPersistence(this.injector.getInstance(Persistence.class));
        converter.run();

        return converter.getTuples();
    }

    private BookingsFile inputStreamToBookingsFile() throws IOException,
            RecognitionException {
        final BookingsLexer lexer =
                new BookingsLexer(new ANTLRInputStream(this.inputStream));
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final BookingsParser parser = new BookingsParser(tokenStream);
        parser.bookingsFile();
        final BookingsFile returnValue = parser.getBookingsFile();
        return returnValue;
    }

    @Override
    public void setInjector(final Injector anInjector) {
        this.injector = anInjector;
    }
}
