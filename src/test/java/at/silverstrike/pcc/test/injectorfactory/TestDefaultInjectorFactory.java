package at.silverstrike.pcc.test.injectorfactory;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

public class TestDefaultInjectorFactory {
    @Test
    public void test01()
    {
        final InjectorFactory injectorFactory = new DefaultInjectorFactory();
        final Injector injector = injectorFactory.createInjector();
        
        Assert.assertNotNull(injector);
        
        try
        {
            injector.getInstance(DebugIdRegistry.class);
        }
        catch (final ConfigurationException exception)
        {
            Assert.fail(exception.getMessage());
        }
    }
}
