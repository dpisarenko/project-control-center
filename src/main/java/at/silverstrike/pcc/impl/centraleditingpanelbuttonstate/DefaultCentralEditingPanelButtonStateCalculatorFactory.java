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

package at.silverstrike.pcc.impl.centraleditingpanelbuttonstate;

import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculatorFactory;

/**
 * @author DP118M
 * 
 */
public class DefaultCentralEditingPanelButtonStateCalculatorFactory implements
        CentralEditingPanelButtonStateCalculatorFactory {

    @Override
    public CentralEditingPanelButtonStateCalculator create() {
        return new DefaultCentralEditingPanelButtonStateCalculator();
    }

}
