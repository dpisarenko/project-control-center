package at.silverstrike.pcc.impl.debugids;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.debugids;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private final static String PREFIX = MessageCodePrefixRegistry.getInstance().getPrefix(debugids);
    
    public final static String M_001_CTOR = PREFIX + "001";

}
