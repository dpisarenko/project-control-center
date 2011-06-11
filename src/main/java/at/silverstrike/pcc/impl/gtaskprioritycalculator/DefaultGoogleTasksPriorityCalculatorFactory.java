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

import at.silverstrike.pcc.api.gtaskprioritycalculator.GoogleTasksPriorityCalculator;
import at.silverstrike.pcc.api.gtaskprioritycalculator.GoogleTasksPriorityCalculatorFactory;

/**
 * @author DP118M
 *
 */
public class DefaultGoogleTasksPriorityCalculatorFactory implements
        GoogleTasksPriorityCalculatorFactory {

    @Override
    public GoogleTasksPriorityCalculator create() {
        return new DefaultGoogleTasksPriorityCalculator();
    }
}
