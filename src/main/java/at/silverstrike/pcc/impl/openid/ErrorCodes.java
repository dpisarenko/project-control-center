package at.silverstrike.pcc.impl.openid;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(MessageCodePrefixRegistry.Module.openid);

    public static final String M_001_AUTH_EXCEPTION = PREFIX + "001";

    public static final String M_002_AUTH_REQUEST_SENDING_FAILURE = PREFIX
            + "002";
}
