package at.silverstrike.pcc.api.mainwindowcontroller;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

public interface MainWindowController extends ModuleWithInjectableDependencies {
	void importFromXML();
	void exportToXML();

}
