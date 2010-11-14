package at.silverstrike.pcc.api.debugids;

public interface DebugIdRegistry {
    String getDebugId(final String aKey) throws DebugIdUniquenessViolation;
}
