package at.silverstrike.pcc.impl.persistence;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.persistence;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private final static String PREFIX = MessageCodePrefixRegistry.getInstance().getPrefix(persistence);
    
    public final static String M_001_OPEN_SESSION = PREFIX + "001";

    public static final String M_002_OPEN_SESSION = PREFIX + "002";
    
    public static final String M_003_OPEN_SESSION = PREFIX + "003";
    
    public static final String M_004_OPEN_SESSION = PREFIX + "004";
    
    public static final String M_005_DAILY_PLAN_NOT_FOUND = PREFIX + "005";
}
