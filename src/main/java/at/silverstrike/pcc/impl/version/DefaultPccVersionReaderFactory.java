package at.silverstrike.pcc.impl.version;

import at.silverstrike.pcc.api.version.PccVersionReader;
import at.silverstrike.pcc.api.version.PccVersionReaderFactory;

public class DefaultPccVersionReaderFactory implements PccVersionReaderFactory {

    @Override
    public PccVersionReader create() {
        return new DefaultPccVersionReader();
    }

}
