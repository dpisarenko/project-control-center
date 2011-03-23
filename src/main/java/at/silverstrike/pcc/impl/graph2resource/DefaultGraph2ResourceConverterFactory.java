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

package at.silverstrike.pcc.impl.graph2resource;

import at.silverstrike.pcc.api.graph2resource.Graph2ResourceConverter;
import at.silverstrike.pcc.api.graph2resource.Graph2ResourceConverterFactory;

/**
 * @author DP118M
 * 
 */
public class DefaultGraph2ResourceConverterFactory implements
        Graph2ResourceConverterFactory {

    @Override
    public Graph2ResourceConverter create() {
        return new DefaultGraph2ResourceConverter();
    }

}
