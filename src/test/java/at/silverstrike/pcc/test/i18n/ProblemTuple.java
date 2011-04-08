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

import java.io.File;
import java.util.List;

/**
 * @author DP118M
 *
 */
class ProblemTuple {
    private String culture;
    private List<File> problematicKeys;
    
    public String getCulture() {
        return culture;
    }
    public void setCulture(final String aCulture) {
        this.culture = aCulture;
    }
    public List<File> getProblematicKeys() {
        return problematicKeys;
    }
    public void setProblematicKeys(final List<File> aProblematicKeys) {
        this.problematicKeys = aProblematicKeys;
    }
}
