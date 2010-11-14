package at.silverstrike.pcc.test.projectscheduler;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.io.File;
import java.util.List;

import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

import com.google.inject.Injector;

public class TestDefaultProjectScheduler {
    private static final String DIR = "src/test/resources/at/silverstrike/"
                            + "pcc/test/projectscheduler/";
    private final static Logger LOGGER =
            LoggerFactory.getLogger(TestDefaultProjectScheduler.class);
    private Helper helper = new Helper();

    @Test
    public void testTaskJugglerIIIInvokation01() {
        /**
         * Create persistence
         */
        final Persistence persistence = new MockPersistence01();

        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule(persistence));
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final ProjectScheduler objectUnderTest =
                injector.getInstance(ProjectScheduler.class);

        assertNotNull(objectUnderTest);

        final ProjectExportInfo projectInfo = objectUnderTest
                .getProjectExportInfo();

        assertNotNull(projectInfo);

        this.helper.fillProjectInfo01(projectInfo);
        objectUnderTest.setInjector(injector);

        /**
         * Set input data
         */
        objectUnderTest
                .setDirectory(DIR);

        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }

        /**
         * Verify that TJ3 was successfully invoked and created an output file
         */
        final File bookingsFile =
                new File(DIR + ProjectScheduler.BOOKINGS_FILE);
        final File deadlinesFile =
                new File(DIR + ProjectScheduler.DEADLINE_CSV_FILE);

        assertTrue("Bookings file doesn't exist.", bookingsFile.exists());
        assertTrue("Deadlines file doesn't exist.", deadlinesFile.exists());
    }
    
    @Test
    public void testRun01() {
        /**
         * Create persistence
         */
        final Persistence persistence = new DefaultPersistence();

        /**
         * Init persistence
         */
        try
        {
            persistence.openSession();   
        }
        catch (final RuntimeException exception)
        {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        catch (final Exception exception)
        {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        
        
        /**
         * Empty the database
         */
        persistence.clearDatabase();
        
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule(persistence));
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final ProjectScheduler objectUnderTest =
                injector.getInstance(ProjectScheduler.class);

        assertNotNull(objectUnderTest);

        final ProjectExportInfo projectInfo = objectUnderTest
                .getProjectExportInfo();

        assertNotNull(projectInfo);

        this.helper.fillProjectInfo02(projectInfo, persistence);
        objectUnderTest.setInjector(injector);

        /**
         * Save all task and resource data in the database
         */
               
        
        /**
         * Set input data
         */
        objectUnderTest
                .setDirectory(DIR);

        /**
         * Verify that our only tasks exists in the database before invokation of the method under test
         */
        
        
        
        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }
        
        /**
         * Verify that now the result of getUncompletedTasksWithEstimatedEndTime
         * contains exactly one task.
         */
        final List<ControlProcess> processes = persistence.getUncompletedTasksWithEstimatedEndTime();
        
        Assert.assertNotNull(processes);
        Assert.assertEquals(1, processes.size());
    }
}
