package at.silverstrike.pcc.impl.mainprocesseditingpanel;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.mainprocesseditingpanel;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private final static String PREFIX = MessageCodePrefixRegistry.getInstance().getPrefix(mainprocesseditingpanel);
    
    public final static String M_001_EXPORT_FAILURE = PREFIX + "001";

}
