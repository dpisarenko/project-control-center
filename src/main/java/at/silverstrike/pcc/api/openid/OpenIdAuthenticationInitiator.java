package at.silverstrike.pcc.api.openid;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.Application;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;

public interface OpenIdAuthenticationInitiator extends SingleActivityModule {
	void setIdentity(final String aIdentity);
	void setRequest(final HttpServletRequest aRequest);
	void setApplication(final Application aApplication);
	boolean isAuthenticationRequestSentSuccessfully();
}
