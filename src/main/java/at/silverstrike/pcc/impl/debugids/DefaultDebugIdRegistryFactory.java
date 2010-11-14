package at.silverstrike.pcc.impl.debugids;

import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistryFactory;

public class DefaultDebugIdRegistryFactory implements DebugIdRegistryFactory {

    @Override
    public DebugIdRegistry create() {
        return new DefaultDebugIdRegistry();
    }
}
