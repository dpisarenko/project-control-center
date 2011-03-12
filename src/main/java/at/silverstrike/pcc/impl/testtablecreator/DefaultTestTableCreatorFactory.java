package at.silverstrike.pcc.impl.testtablecreator;

import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreatorFactory;

public class DefaultTestTableCreatorFactory implements TestTableCreatorFactory {

    @Override
    public TestTableCreator create() {
        return new DefaultTestTableCreator();
    }
}
