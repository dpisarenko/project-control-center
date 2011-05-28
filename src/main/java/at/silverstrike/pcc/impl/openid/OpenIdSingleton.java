package at.silverstrike.pcc.impl.openid;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenIdSingleton {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(OpenIdSingleton.class);

    private static OpenIdSingleton instance;

    private HttpProcessor httpProcessor;
    private OpenidService openidService;

    public static OpenIdSingleton getInstance() {
        if (instance == null) {
            instance = new OpenIdSingleton();
        }
        return instance;
    }

    private OpenIdSingleton() {

    }

    public void onStartRequest(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
        httpProcessor = new HttpProcessor(aRequest);
        if (!httpProcessor.isRepaint()) {
            try {
                openidService = httpProcessor.restoreService();
                LOGGER.debug(TextUtils
                        .proxyPropertiesToString(httpProcessor
                                .getProxySettings()));
                if (httpProcessor.isReturned()) {
                    final Map<String, String> parametersMap =
                            httpProcessor.getRequestParamsMap();
                    final AuthSuccess success =
                            openidService.processReturn(parametersMap,
                                    httpProcessor.getReturnUrl());

                    final OpenidModel model =
                            openidService.extractOpenidData(success);
                    httpProcessor.setModel(model);
                    httpProcessor.saveService(openidService);
                    aResponse.sendRedirect(httpProcessor.getRootUrl());
                }
            } catch (final Exception exception) {
                LOGGER.error("", exception);
                httpProcessor.cleanIdentity();
            }
        }

    }

    public void initiateInvitationRequestAuth() {
        try {
            openidService.performDiscoveryOnUserSuppliedIdentifier("https://www.google.com/accounts/o8/id");

            httpProcessor.saveService(openidService);

            final AuthRequest authRequest = openidService.createOpenIdAuthRequest(httpProcessor.getReturnUrl());
        }
        catch (final Exception exception) {
            LOGGER.error("", exception);
        }
    }
}
