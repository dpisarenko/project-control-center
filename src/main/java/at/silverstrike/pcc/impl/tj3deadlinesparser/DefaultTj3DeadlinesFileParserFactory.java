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

package at.silverstrike.pcc.impl.tj3deadlinesparser;

import at.silverstrike.pcc.api.jruby.AbstractRubyImplementationFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParser;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultTj3DeadlinesFileParserFactory extends
        AbstractRubyImplementationFactory<Tj3DeadlinesFileParser> implements
        Tj3DeadlinesFileParserFactory {

    @Override
    protected final String getInterfacename() {
        return "tj3deadlinesparser";
    }

    @Override
    protected final String getModuleName() {
        return "Tj3DeadlinesFileParser";
    }
}
