package at.silverstrike.pcc.impl.testtablecreator;

import java.util.List;

import com.vaadin.ui.Table;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;

class DefaultTestTableCreator implements TestTableCreator {
    private String[] names;
    private List<String[]> data;
    private Table table;

    public DefaultTestTableCreator() {
    }

    @Override
    public void run() throws PccException {
        table = new Table();

        for (String name : names)
            table.addContainerProperty(name, String.class, null);

        for (String[] row : data)
            table.addItem(row, null);
    }

    @Override
    public void setData(final List<String[]> aData) {
        if (aData != null)
            data = aData;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public void setColumnNames(final String[] aNames) {
        if (aNames != null)
            names = aNames;
    }
}
