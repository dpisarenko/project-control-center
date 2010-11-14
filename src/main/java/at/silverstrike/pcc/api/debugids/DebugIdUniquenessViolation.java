package at.silverstrike.pcc.api.debugids;

public class DebugIdUniquenessViolation extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DebugIdUniquenessViolation(final String aKey)
    {
        super("DebugIdUniquenessViolation: " + aKey);
    }
}
