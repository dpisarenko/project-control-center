package at.silverstrike.pcc.impl.openid;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.util.HttpClientFactory;
import org.openid4java.util.ProxyProperties;

/**
 * Class performs OpenId operations.
 */
class OpenidService {

	private ProxyProperties proxyProperties;
	private ConsumerManager consumerManager;
	private DiscoveryInformation discoveryInformation;

	public OpenidService() {
	}

	public OpenidService(ConsumerManager consumerManager,
			DiscoveryInformation discoveryInformation) {
		this.consumerManager = consumerManager;
		this.discoveryInformation = discoveryInformation;
	}

	@SuppressWarnings("unchecked")
	public void performDiscoveryOnUserSuppliedIdentifier(
			final String userSuppliedIdentifier) throws DiscoveryException,
			ConsumerException {

		consumerManager = getConsumerManager();

		// Perform discover on the User-Supplied Identifier
		List<DiscoveryInformation> discoveries = consumerManager
				.discover(userSuppliedIdentifier);
		// Pass the discoveries to the associate() method...
		this.discoveryInformation = consumerManager.associate(discoveries);
	}

	public AuthRequest createOpenIdAuthRequest(String returnToUrl)
			throws MessageException, ConsumerException, DiscoveryException {

		// Create the AuthRequest object
		AuthRequest ret = getConsumerManager().authenticate(
				discoveryInformation, returnToUrl);

		FetchRequest fetch = FetchRequest.createFetchRequest();
		fetch.addAttribute("Email", "http://schema.openid.net/contact/email",
				true);

		ret.addExtension(fetch);

		return ret;

	}

	@SuppressWarnings("rawtypes")
	public AuthSuccess processReturn(Map pageParameters, String returnToUrl)
			throws MessageException, DiscoveryException, AssociationException,
			ConsumerException {

		ParameterList response = new ParameterList(pageParameters);
		VerificationResult verificationResult;
		verificationResult = getConsumerManager().verify(returnToUrl, response,
				discoveryInformation);

		Identifier verifiedIdentifier = verificationResult.getVerifiedId();

		if (verifiedIdentifier != null) {
			return (AuthSuccess) verificationResult.getAuthResponse();
		} else {
			throw new RuntimeException("Verification fails:"
					+ verificationResult.getStatusMsg());
		}
	}

	@SuppressWarnings("rawtypes")
	public OpenidModel extractOpenidData(AuthSuccess authSuccess)
			throws DiscoveryException, MessageException {
		OpenidModel model = new OpenidModel();
		model.setOpenId(authSuccess.getIdentity());

		if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
			FetchResponse fetchResp = (FetchResponse) authSuccess
					.getExtension(AxMessage.OPENID_NS_AX);

			@SuppressWarnings("rawtypes")
			List aliases = fetchResp.getAttributeAliases();
			for (Iterator iter = aliases.iterator(); iter.hasNext();) {
				String alias = (String) iter.next();
				@SuppressWarnings("rawtypes")
				List values = fetchResp.getAttributeValues(alias);

				if (!values.isEmpty()) {
					model.getAttributes().put(alias, (String) values.get(0));
				}
			}
		}
		return model;
	}

	public ProxyProperties getProxyProperties() {
		return proxyProperties;
	}

	public void setProxyProperties(ProxyProperties proxyProperties) {
		this.proxyProperties = proxyProperties;
	}

	public ConsumerManager getConsumerManager() {
		try {
			if (consumerManager == null) {
				this.consumerManager = new ConsumerManager();

				if (proxyProperties != null) {
					HttpClientFactory.setProxyProperties(proxyProperties);
				}

				consumerManager
						.setAssociations(new InMemoryConsumerAssociationStore());
				consumerManager.setNonceVerifier(new InMemoryNonceVerifier(
						10000));
			}

			return consumerManager;
		} catch (ConsumerException e) {
			throw new RuntimeException(e);
		}
	}

	public void setConsumerManager(ConsumerManager consumerManager) {
		this.consumerManager = consumerManager;
	}

	public DiscoveryInformation getDiscoveryInformation() {
		return discoveryInformation;
	}

	public void setDiscoveryInformation(
			DiscoveryInformation discoveryInformation) {
		this.discoveryInformation = discoveryInformation;
	}
}
