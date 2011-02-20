package at.silverstrike.pcc.api.openid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;

public interface OpenIdAuthenticationResponder extends SingleActivityModule {
	void setRequest(final HttpServletRequest aRequest);
	void setResponse(final HttpServletResponse aResponse);
	boolean isValidationSuccessful();
	String getIdentity();
}
