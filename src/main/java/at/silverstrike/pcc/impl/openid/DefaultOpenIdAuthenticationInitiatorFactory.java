package at.silverstrike.pcc.impl.openid;

import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiator;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiatorFactory;

public class DefaultOpenIdAuthenticationInitiatorFactory implements
		OpenIdAuthenticationInitiatorFactory {

	@Override
	public OpenIdAuthenticationInitiator create() {
		return new DefaultOpenIdAuthenticationInitiator();
	}

}
