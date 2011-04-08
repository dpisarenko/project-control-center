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
class ProblemTuple {
    private String culture;
    private List<String> problematicKeys;

    public ProblemTuple() {
        this.problematicKeys = new LinkedList<String>();
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(final String aCulture) {
        this.culture = aCulture;
    }

    public List<String> getProblematicKeys() {
        return problematicKeys;
    }

    public void setProblematicKeys(final List<String> aProblematicKeys) {
        this.problematicKeys = aProblematicKeys;
    }
}
