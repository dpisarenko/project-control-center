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

package at.silverstrike.pcc.impl.gtaskprioritycalculator;

/**
 * @author DP118M
 * 
 */
class GoogleTaskIdAndPriorityTuple implements
        Comparable<GoogleTaskIdAndPriorityTuple> {
    private String id;
    private String position;

    public String getId() {
        return id;
    }

    public void setId(final String aId) {
        this.id = aId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String aPosition) {
        this.position = aPosition;
    }

    @Override
    public int compareTo(final GoogleTaskIdAndPriorityTuple aAnotherTuple) {
        final long criterion1 = getSortCriterion(this);
        final long criterion2 = getSortCriterion(aAnotherTuple);

        return (int) (criterion1 - criterion2);
    }

    private long getSortCriterion(final GoogleTaskIdAndPriorityTuple aTuple) {
        return Long.parseLong(aTuple.getPosition());
    }
}
