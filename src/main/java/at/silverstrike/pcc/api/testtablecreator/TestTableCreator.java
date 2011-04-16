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

package at.silverstrike.pcc.api.testtablecreator;

import java.util.List;

import ru.altruix.commons.api.conventions.SingleActivityModule;


import com.vaadin.ui.Table;

public interface TestTableCreator extends SingleActivityModule {
    void setColumnNames(final String[] aNames);

    void setData(final List<String[]> aData);

    Table getTable();

}
