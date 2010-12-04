/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/
package at.silverstrike.pcc.impl.jruby;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dmitri Pisarenko
 *
 */
public class SandBox {
	private Logger LOGGER = LoggerFactory.getLogger(SandBox.class);
	public void run()
	{
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("jruby");
		try
		{
			engine.eval("puts 'Hello, Ruby!'");
		}
		catch (final ScriptException exception)
		{
			LOGGER.error("", exception);
		}
	}
	public static void main(final String[] anArgs)
	{
		final SandBox app = new SandBox();
		
		app.run();
	}
}
