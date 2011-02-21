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

import at.silverstrike.pcc.api.jruby.JRubySandBox;
import at.silverstrike.pcc.api.jruby.AbstractRubyImplementationFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultJRubySandBoxFactory extends
        AbstractRubyImplementationFactory<JRubySandBox> {

    @Override
    protected final String getInterfacename() {
        return "jruby";
    }

    @Override
    protected final String getModuleName() {
        return "JRubySandBox";
    }
}
