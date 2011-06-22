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

package at.silverstrike.pcc.api.gtasktitleparser;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface GoogleTaskTitleParser extends SingleActivityModule {
    void setTitle(final String aTitle);
    
    boolean isLabelSpecified();
    String getLabel();
}
