package at.silverstrike.pcc.api.debugids;

public class DebugIdKeyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DebugIdKeyNotFoundException(final String aKey)
    {
        super("Debug ID key not found: " + aKey);
    }
}
