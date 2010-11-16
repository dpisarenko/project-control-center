package at.silverstrike.pcc.impl.webguibus;

import at.silverstrike.pcc.api.webguibus.WebGuiBusMessageFactory;
import at.silverstrike.pcc.api.webguibus.WorkerAddedMessage;

public class DefaultWebGuiBusMessageFactory implements WebGuiBusMessageFactory {
    @Override
    public WorkerAddedMessage createWorkerAddedMessage() {
        return new DefaultWorkerAddedMessage();
    }
}