package at.silverstrike.pcc.api.export2tj3;

import at.silverstrike.pcc.api.conventions.PccException;

/**
 * This exception is thrown, if TaskJugglerExporter is invoked without
 * valid process information.
 */
public class NoProcessesException extends PccException {
    private static final long serialVersionUID = 1L;

    public NoProcessesException()
    {
        super("No processes were given");
    }
}
