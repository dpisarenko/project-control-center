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

package at.silverstrike.pcc.test.i18n;

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

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public List<String> getNonBlankKeys() {
        return nonBlankKeys;
    }

    public void setNonBlankKeys(List<String> nonBlankKeys) {
        this.nonBlankKeys = nonBlankKeys;
    }
}
