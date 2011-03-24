package at.silverstrike.pcc.impl.mainwindowcontroller;

import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;

public class DefaultMainWindowControllerFactory implements
		MainWindowControllerFactory {

	@Override
	public MainWindowController create() {
		return new DefaultMainWindowController();
	}

}
