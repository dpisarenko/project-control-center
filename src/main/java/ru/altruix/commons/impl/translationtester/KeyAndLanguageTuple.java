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

package ru.altruix.commons.impl.translationtester;

import java.util.LinkedList;
import java.util.List;

/**
 * @author DP118M
 * 
 */
class KeyAndLanguageTuple {
    private String culture;
    private List<String> nonBlankKeys;

    public KeyAndLanguageTuple() {
        this.nonBlankKeys = new LinkedList<String>();
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(final String aCulture) {
        this.culture = aCulture;
    }

    public List<String> getNonBlankKeys() {
        return nonBlankKeys;
    }

    public void setNonBlankKeys(final List<String> aNonBlankKeys) {
        this.nonBlankKeys = aNonBlankKeys;
    }
}
