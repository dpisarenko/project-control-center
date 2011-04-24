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

package at.silverstrike.pcc.test.tj3bookingsparser;

import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParser;
import at.silverstrike.pcc.impl.embeddedfilereading.DefaultEmbeddedFileReaderFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultBookingsFile2BookingsFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultTj3BookingsParserFactory;
import at.silverstrike.pcc.test.mockpersistence.MockBooking;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;

import com.google.inject.AbstractModule;

class MockInjectorModule extends AbstractModule {

    /**
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
        bind(Tj3BookingsParser.class).toInstance(
                new DefaultTj3BookingsParserFactory().create());
        bind(BookingsFile2BookingsFactory.class).toInstance(
                new DefaultBookingsFile2BookingsFactory());
        bind(EmbeddedFileReader.class).toInstance(
                new DefaultEmbeddedFileReaderFactory().create());
        bind(Persistence.class).toInstance(new MockPersistenceAdapter() {

            @Override
            public Booking createBooking() {
                return new MockBooking();
            }

            @Override
            public void increasePriority(Long parentProjectId) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void decreasePriority(Long parentProjectId) {
                // TODO Auto-generated method stub
                
            }
        });
    }
}
