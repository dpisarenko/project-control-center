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

package at.silverstrike.pcc.api.jruby;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.Factory;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Dmitri Pisarenko
 * 
 */
public abstract class AbstractRubyImplementationFactory<X> implements
        Factory<X>, ModuleWithInjectableDependencies {

    private static final String RUBY_CONSTRUCTOR = ".new";
    private static final String RUBY_EXTENSION = ".rb";
    private static final String IMPLEMENTATION_PREFIX = "Default";
    private static final String RUBY_DIRECTORY = "rb/";
    private static final Logger LOGGER =
        LoggerFactory.getLogger(AbstractRubyImplementationFactory.class);
    private Injector injector;

    @SuppressWarnings("unchecked")
    private X createRubyImplementation(final String aModule,
            final String anInterfacename) {
        final ScriptEngine jruby =
                new ScriptEngineManager().getEngineByName("jruby");
        X retVal = null;

        try {
            final EmbeddedFileReader reader =
                    this.injector.getInstance(EmbeddedFileReader.class);
            jruby
                    .eval(reader.readEmbeddedFile(RUBY_DIRECTORY
                            + anInterfacename
                            + "/" + IMPLEMENTATION_PREFIX + aModule
                            + RUBY_EXTENSION));

            retVal =
                    (X) jruby.eval(IMPLEMENTATION_PREFIX + aModule
                            + RUBY_CONSTRUCTOR);

            return retVal;
        } catch (final ScriptException exception) {
            LOGGER.error("", exception);
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
        return retVal;
    }

    @Inject
    public void setInjector(final Injector anInjector) {
        this.injector = anInjector;
    }

    @Override
    public X create() {
        return createRubyImplementation(getModuleName(), getInterfacename());
    }

    protected abstract String getInterfacename();

    protected abstract String getModuleName();
}
