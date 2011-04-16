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

package at.silverstrike.pcc.impl.testtablecreator;

import java.util.List;

import ru.altruix.commons.api.di.PccException;

import com.vaadin.ui.Table;

import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;

class DefaultTestTableCreator implements TestTableCreator {
    private String[] names = null;
    private List<String[]> data = null;
    private Table table;

    public DefaultTestTableCreator() {
    }

    @Override
    public void run() throws PccException {
        table = new Table();

        for (final String name : names) {
            table.addContainerProperty(name, String.class, null);
        }

        for (final String[] row : data) {
            table.addItem(row, null);
        }
    }

    @Override
    public void setData(final List<String[]> aData) {
        if (aData != null) {
            data = aData;
        }

    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public void setColumnNames(final String[] aNames) {
        if (aNames != null) {
            names = aNames;
        }
    }
}
