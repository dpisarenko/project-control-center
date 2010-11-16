package at.silverstrike.pcc.api.webguibus;

public interface WebGuiBus {
    void addListener(final WebGuiBusListener aListener);
    
    void broadcastWorkerAddedMessage(final WorkerAddedMessage aMessage);
}
