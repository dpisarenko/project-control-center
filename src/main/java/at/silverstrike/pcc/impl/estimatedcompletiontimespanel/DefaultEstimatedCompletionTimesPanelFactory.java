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

package at.silverstrike.pcc.impl.estimatedcompletiontimespanel;

import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanel;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultEstimatedCompletionTimesPanelFactory implements
        EstimatedCompletionTimesPanelFactory {

    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final EstimatedCompletionTimesPanel create() {
        return new DefaultEstimatedCompletionTimesPanel();
    }

}
