package at.silverstrike.pcc.api.export2tj3;

import at.silverstrike.pcc.api.conventions.PccException;

/**
 * This exception is thrown, if TaskJugglerExporter is invoked without
 * the information about the project to be exported.
 */
public class NoProjectExportInfoException extends PccException {
    private static final long serialVersionUID = 1L;

    public NoProjectExportInfoException() {
        super("No project info specified");
    }
}
