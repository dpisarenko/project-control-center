package at.silverstrike.pcc.impl.entrywindow;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiator;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.i18n.TM;

class AuthenticateButtonListener implements Button.ClickListener {
	private static final long serialVersionUID = 1L;

	private Injector injector;
	private TextField openIdTextField;
	private HttpServletRequest request;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticateButtonListener.class);

	public void setOpenIdTextField(final TextField aOpenIdTextField) {
		this.openIdTextField = aOpenIdTextField;
	}

	public void setInjector(final Injector aInjector) {
		this.injector = aInjector;
	}

	@Override
	public void buttonClick(final ClickEvent aEvent) {
		final String identity = (String) this.openIdTextField.getValue();
		final TPTApplication app = TPTApplication.getCurrentApplication();
		
		if (StringUtils.isBlank(identity)) {	
			showErrorMessage(app, TM.get("entrywindow.6-messagebox-text"));
		}

		final OpenIdAuthenticationInitiator authInitiator = this.injector
				.getInstance(OpenIdAuthenticationInitiator.class);
		authInitiator.setApplication(app);
		authInitiator.setIdentity(identity);
		authInitiator.setRequest(this.request);
		
		try {
			authInitiator.run();
			
			if (!authInitiator.isAuthenticationRequestSentSuccessfully())
			{
				showErrorMessage(app, TM.get("entrywindow.7-authinitiator-failure"));				
			}
		} catch (final PccException exception) {
			LOGGER.error("", exception);
			showErrorMessage(app, TM.get("entrywindow.7-authinitiator-failure"));
		}
	}

	private void showErrorMessage(final TPTApplication aApp, String aMessage) {
		final OptionDialog messageBox = new OptionDialog(
				aApp);
		messageBox.showMessageDialog(
				TM.get("entrywindow.5-messagebox-title"),
				aMessage, null);
	}

	public void setRequest(final HttpServletRequest aRequest) {
		this.request = aRequest;
	}

}
