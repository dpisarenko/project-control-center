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

package at.silverstrike.pcc.impl.gtaskrelevance;

import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculator;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculatorFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultIsGoogleTaskRelevantCalculatorFactory implements
        IsGoogleTaskRelevantCalculatorFactory {

    @Override
    public IsGoogleTaskRelevantCalculator create() {
        return new DefaultIsGoogleTaskRelevantCalculator();
    }

}
