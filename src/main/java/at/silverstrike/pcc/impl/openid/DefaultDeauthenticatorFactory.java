package at.silverstrike.pcc.impl.openid;

import at.silverstrike.pcc.api.openid.Deauthenticator;
import at.silverstrike.pcc.api.openid.DeauthenticatorFactory;

public class DefaultDeauthenticatorFactory implements DeauthenticatorFactory {

	@Override
	public Deauthenticator create() {
		return new DefaultDeauthenticator();
	}

}
