package at.silverstrike.pcc.test.persistence;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;

public class TestDefaultPersistence {
    @Test
    public void test01()
    {
        final Persistence persistence = new DefaultPersistence();
        
        try
        {
            persistence.openSession();   
        }
        catch (final RuntimeException exception)
        {
            Assert.fail(exception.getMessage());
        }
        catch (final Exception exception)
        {
            Assert.fail(exception.getMessage());
        }
        
        try
        {
            persistence.getDailyPlan(new Date(), "DP");
        }
        catch (final RuntimeException exception)
        {
            Assert.fail(exception.getMessage());
        }
        catch (final Exception exception)
        {
            Assert.fail(exception.getMessage());
        }

    }
}
