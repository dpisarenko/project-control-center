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

package at.silverstrike.pcc.test.tj3bookingsparser;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingStatement;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile;
import at.silverstrike.pcc.api.tj3bookingsparser.IndBooking;
import at.silverstrike.pcc.api.tj3bookingsparser.SupplementStatement;
import at.silverstrike.pcc.impl.tj3bookingsparser.grammar.BookingsLexer;
import at.silverstrike.pcc.impl.tj3bookingsparser.grammar.BookingsParser;

public class TestBookingsParser {
    private static final String DIR = "src/test/resources/at/silverstrike/"
            + "pcc/test/tj3bookingsparser/";
    private final static Logger LOGGER =
            LoggerFactory.getLogger(TestBookingsParser.class);

    @Before
    public void setupLogger() {
    }

    @Test
    public void test01() {
        BookingsFile bookingsFile = null;
        InputStream inputStream = null;
        try {
            inputStream = FileUtils.openInputStream(new File(DIR + "test01"));
            final BookingsLexer lexer =
                    new BookingsLexer(new ANTLRInputStream(inputStream));
            final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            final BookingsParser parser = new BookingsParser(tokenStream);

            parser.bookingsFile();

            bookingsFile = parser.getBookingsFile();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        assertNotNull(bookingsFile);

        final List<SupplementStatement> supplementStatements =
                bookingsFile.getSupplementStatements();

        assertNotNull(supplementStatements);
        assertEquals(1, supplementStatements.size());

        final SupplementStatement supplementStatement =
                supplementStatements.get(0);

        final List<BookingStatement> bookingStatements =
                supplementStatement.getBookingStatements();

        assertNotNull(bookingStatements);
        assertEquals(1, bookingStatements.size());

        final BookingStatement bookingStatement = bookingStatements.get(0);

        assertEquals("R1210", bookingStatement.getResource());

        final List<IndBooking> indBookings = bookingStatement.getIndBookings();

        assertNotNull(indBookings);
        assertEquals(1, indBookings.size());

        final IndBooking indBooking = indBookings.get(0);

        LOGGER.debug("indBooking.getDuration(): " + indBooking.getDuration());

        Assert
                .assertEquals("2010-10-25-09:00-+0200", indBooking
                        .getStartTime());
        Assert.assertEquals("2.75h", indBooking.getDuration());
    }

}
