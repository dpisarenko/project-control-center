package at.silverstrike.pcc.test.tj3bookingsparser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

public class TestDefaultBookingsFile2BookingsFactory {

    @Before
    public void setupLogger() {
    }

    @Test
    public void test01()
    {
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule());
        final Injector injector = injectorFactory.createInjector();

        final BookingsFile2BookingsFactory objectUnderTest = injector.getInstance(BookingsFile2BookingsFactory.class);
        Assert.assertNotNull(objectUnderTest.create());
    }
}
