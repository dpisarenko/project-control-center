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

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;

class DefaultCultureToLanguageMapper implements CultureToLanguageMapper {
    private static final String RUSSIAN = "ru";
    private static final String ENGLISH = "en";
    private String culture = "";
    private String language;

    @Override
    public void run() throws PccException {
        if (StringUtils.isBlank(this.culture)) {
            this.language = ENGLISH;
        } else if (this.culture.toLowerCase(Locale.ROOT).startsWith(RUSSIAN)) {
            this.language = RUSSIAN;
        } else {
            this.language = ENGLISH;
        }
    }

    @Override
    public void setCulture(final String aCulture) {
        this.culture = aCulture;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }
}
