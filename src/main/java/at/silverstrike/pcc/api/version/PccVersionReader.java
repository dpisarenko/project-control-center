package at.silverstrike.pcc.api.version;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;

public interface PccVersionReader extends SingleActivityModule {
    String getVersion();
}
