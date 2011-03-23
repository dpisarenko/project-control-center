package at.silverstrike.pcc.impl.mainwindowcontroller;

import com.google.inject.Injector;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;

public class DefaultMainWindowController implements MainWindowController {

	private Injector injector = null;
	
	@Override
	public void setInjector(Injector aInjector) {
		this.injector = aInjector;

	}

	@Override
	public void importFromXML() {
		TPTApplication.getCurrentApplication().getMainWindow()
				.showNotification("111Test for Import111");

	}

	@Override
	public void exportToXML() {
		TPTApplication.getCurrentApplication().getMainWindow()
		.showNotification("222Test for Export222");

	}

}
