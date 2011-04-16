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

import org.apache.commons.lang.builder.ToStringBuilder;

import ru.altruix.commons.api.translationtester.ProblemTuple;

/**
 * @author DP118M
 * 
 */
class DefaultProblemTuple implements ProblemTuple {
    private String culture;
    private List<String> problematicKeys;

    public DefaultProblemTuple() {
        this.problematicKeys = new LinkedList<String>();
    }

    @Override
    public String getCulture() {
        return culture;
    }

    public void setCulture(final String aCulture) {
        this.culture = aCulture;
    }

    @Override
    public List<String> getProblematicKeys() {
        return problematicKeys;
    }

    public void setProblematicKeys(final List<String> aProblematicKeys) {
        this.problematicKeys = aProblematicKeys;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);

        return builder.append("culture", this.culture)
                .append("problematicKeys", this.problematicKeys).toString();
    }

}
