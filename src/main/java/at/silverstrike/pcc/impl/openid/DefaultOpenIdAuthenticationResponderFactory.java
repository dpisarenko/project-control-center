/**
 * 
 */
package at.silverstrike.pcc.impl.openid;

import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponderFactory;

/**
 * @author DP118M
 *
 */
public class DefaultOpenIdAuthenticationResponderFactory implements
		OpenIdAuthenticationResponderFactory {

	@Override
	public OpenIdAuthenticationResponder create() {
		return new DefaultOpenIdAuthenticationResponder();
	}

}
