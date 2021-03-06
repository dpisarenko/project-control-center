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

package at.silverstrike.pcc.impl.culture2lang;

import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapperFactory;

public class DefaultCultureToLanguageMapperFactory implements
        CultureToLanguageMapperFactory {

    @Override
    public final CultureToLanguageMapper create() {
        return new DefaultCultureToLanguageMapper();
    }

}
