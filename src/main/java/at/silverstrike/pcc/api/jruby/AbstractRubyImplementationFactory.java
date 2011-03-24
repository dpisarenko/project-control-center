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
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractRubyImplementationFactory.class);
	private Injector injector;

	@SuppressWarnings("unchecked")
	private X createRubyImplementation(final String aModule,
			final String aInterfacename) {
		final ScriptEngine jruby = new ScriptEngineManager()
				.getEngineByName("jruby");
		X retVal = null;

		try {
			final EmbeddedFileReader reader = this.injector
					.getInstance(EmbeddedFileReader.class);
			jruby.eval(reader.readEmbeddedFile(RUBY_DIRECTORY + aInterfacename
					+ "/" + IMPLEMENTATION_PREFIX + aModule + RUBY_EXTENSION));

			retVal = (X) jruby.eval(IMPLEMENTATION_PREFIX + aModule
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
	public final void setInjector(final Injector aInjector) {
		this.injector = aInjector;
	}

	@Override
	public final X create() {
		return createRubyImplementation(getModuleName(), getInterfacename());
	}

	protected abstract String getInterfacename();

	protected abstract String getModuleName();
}
