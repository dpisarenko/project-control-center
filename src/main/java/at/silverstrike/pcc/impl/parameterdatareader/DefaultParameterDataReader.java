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

package at.silverstrike.pcc.impl.parameterdatareader;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReader;

class DefaultParameterDataReader implements ParameterDataReader {
    private static final String DEFAULT_CULTURE = "en";
    private static final String CULTURE = "culture";

    private Map<String, String[]> parameters;
    private String culture = DEFAULT_CULTURE;

    @Override
    public void run() throws PccException {
        if (this.parameters != null) {
            final String[] values = this.parameters.get(CULTURE);

            if ((values != null) && (values.length > 0)) {
                final String curCulture = values[0];

                if (!StringUtils.isBlank(curCulture)) {
                    this.culture = curCulture;
                } else {
                    this.culture = DEFAULT_CULTURE;
                }
            }
        }
    }

    @Override
    public void setParameters(final Map<String, String[]> aParameters) {
        this.parameters = aParameters;
    }

    @Override
    public String getCulture() {
        return this.culture;
    }
}
