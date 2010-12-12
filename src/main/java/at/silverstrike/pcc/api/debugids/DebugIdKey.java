package at.silverstrike.pcc.api.debugids;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module;

public interface DebugIdKey {
    void setModule(final Module aModule);
    Module getModule();
    
    void setKey(final String aKey);
    String getKey();
}
