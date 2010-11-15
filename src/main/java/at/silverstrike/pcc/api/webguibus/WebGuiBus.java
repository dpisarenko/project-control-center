package at.silverstrike.pcc.api.webguibus;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

public interface WebGuiBus extends ModuleWithInjectableDependencies {
    void addListener(final WebGuiBusListener aListener);
}
