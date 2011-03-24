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
    private static final Logger LOGGER = LoggerFactory.getLogger(SandBox.class);

    public final void run() {
        final ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine engine = factory.getEngineByName("jruby");
        try {
            engine.eval("puts 'Hello, Ruby!'");
        } catch (final ScriptException exception) {
            LOGGER.error("", exception);
        }
    }

    public static void main(final String[] aArgs) {
        final SandBox app = new SandBox();

        app.run();
    }
}
