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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class performs OpenId operations.
 */
class OpenidService {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(OpenidService.class);

    private ProxyProperties proxyProperties;
    private ConsumerManager consumerManager;
    private DiscoveryInformation discoveryInformation;

    public OpenidService() {
    }

    public OpenidService(final ConsumerManager aConsumerManager,
            final DiscoveryInformation aDiscoveryInformation) {
        this.consumerManager = aConsumerManager;
        this.discoveryInformation = aDiscoveryInformation;
    }

    @SuppressWarnings("unchecked")
    public void performDiscoveryOnUserSuppliedIdentifier(
            final String aUserSuppliedIdentifier) throws DiscoveryException,
            ConsumerException {

        consumerManager = getConsumerManager();

        // Perform discover on the User-Supplied Identifier
        final List<DiscoveryInformation> discoveries =
                consumerManager.discover(aUserSuppliedIdentifier);
        // Pass the discoveries to the associate() method...
        this.discoveryInformation = consumerManager.associate(discoveries);
    }

    public AuthRequest createOpenIdAuthRequest(final String aReturnToUrl)
            throws MessageException, ConsumerException, DiscoveryException {

        // Create the AuthRequest object
        final AuthRequest ret =
                getConsumerManager().authenticate(discoveryInformation,
                        aReturnToUrl);

        final FetchRequest fetch = FetchRequest.createFetchRequest();
        fetch.addAttribute("Email", "http://schema.openid.net/contact/email",
                true);

        ret.addExtension(fetch);

        return ret;

    }

    @SuppressWarnings("rawtypes")
    public AuthSuccess processReturn(final Map aPageParameters,
            final String aReturnToUrl)
            throws MessageException, DiscoveryException, AssociationException,
            ConsumerException {
        final ParameterList response = new ParameterList(aPageParameters);
        final VerificationResult verificationResult =
                getConsumerManager().verify(aReturnToUrl, response,
                        discoveryInformation);

        final Identifier verifiedIdentifier =
                verificationResult.getVerifiedId();

        if (verifiedIdentifier != null) {
            return (AuthSuccess) verificationResult.getAuthResponse();
        } else {
            throw new RuntimeException("Verification fails:"
                    + verificationResult.getStatusMsg());
        }
    }

    @SuppressWarnings("rawtypes")
    public OpenidModel extractOpenidData(final AuthSuccess aAuthSuccess)
            throws DiscoveryException, MessageException {
        final OpenidModel model = new OpenidModel();
        model.setOpenId(aAuthSuccess.getIdentity());

        if (aAuthSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
            FetchResponse fetchResp =
                    (FetchResponse) aAuthSuccess
                            .getExtension(AxMessage.OPENID_NS_AX);

            List aliases = fetchResp.getAttributeAliases();
            for (final Iterator iter = aliases.iterator(); iter.hasNext();) {
                final String alias = (String) iter.next();
                final List values = fetchResp.getAttributeValues(alias);

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

    public void setProxyProperties(final ProxyProperties aProxyProperties) {
        this.proxyProperties = aProxyProperties;
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
        } catch (final ConsumerException exception) {
            LOGGER.error(ErrorCodes.M_003_CONSUMER_MANAGER, exception);
            return null;
        }
    }

    public void setConsumerManager(final ConsumerManager aConsumerManager) {
        this.consumerManager = aConsumerManager;
    }

    public DiscoveryInformation getDiscoveryInformation() {
        return discoveryInformation;
    }

    public void setDiscoveryInformation(
            final DiscoveryInformation aDiscoveryInformation) {
        this.discoveryInformation = aDiscoveryInformation;
    }
}
