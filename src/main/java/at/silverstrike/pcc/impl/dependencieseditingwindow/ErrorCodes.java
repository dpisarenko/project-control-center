package at.silverstrike.pcc.impl.dependencieseditingwindow;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.dependencieseditingwindow;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(dependencieseditingwindow);

    public static final String M_001_TEST_TABLE_CREATION = PREFIX + "001";

    private ErrorCodes() {

    }
}
