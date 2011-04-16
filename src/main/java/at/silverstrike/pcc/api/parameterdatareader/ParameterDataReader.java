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

package at.silverstrike.pcc.api.parameterdatareader;

import java.util.Map;

import ru.altruix.commons.api.conventions.SingleActivityModule;

public interface ParameterDataReader extends SingleActivityModule {
    void setParameters(final Map<String, String[]> aParameters);

    String getCulture();
}
