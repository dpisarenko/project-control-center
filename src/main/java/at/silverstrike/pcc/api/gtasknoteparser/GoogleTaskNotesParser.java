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

package at.silverstrike.pcc.api.gtasknoteparser;

import java.util.List;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 * 
 */
public interface GoogleTaskNotesParser extends SingleActivityModule {
    void setNotes(final String aNotes);

    boolean isEffortSpecified();

    double getEffortInHours();
    
    boolean isLabelSpecified();
    
    String getLabel();
    
    boolean arePredecessorsSpecified();
    
    List<String> getPredecessorLabels();
}
