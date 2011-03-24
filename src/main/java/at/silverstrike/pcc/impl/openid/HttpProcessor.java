/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/
package at.silverstrike.pcc.impl.openid;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.util.ProxyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class HttpProcessor {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HttpProcessor.class);

    private final HttpServletRequest request;
    private static final String OPENID_DISCOVERY_KEY = "openid.discovery";
    private static final String OPENID_MODEL_KEY = "openid.model";
    private static final String OPENID_CONSUMER_MANAGER_KEY =
            "openid.consumer-manager";
    private static final String OPENID_PROXY_PROPERTIES =
            "openid.proxy-properties";
    private static final String ROOT_URL = "pcc";
    private static final String REPAINT_MESSAGE = "repaintAll";

    /**
     * 
     * @param aRequest
     */
    public HttpProcessor(final HttpServletRequest aRequest) {
        this.request = aRequest;
    }

    public void setDiscoveryInfo(final DiscoveryInformation aInfo) {
        final HttpSession session = request.getSession();
        session.setAttribute(OPENID_DISCOVERY_KEY, aInfo);
    }

    public DiscoveryInformation getDiscoveryInfo() {
        final HttpSession session = request.getSession();
        return (DiscoveryInformation) session
                .getAttribute(OPENID_DISCOVERY_KEY);
    }

    public void setModel(final OpenidModel aModel) {
        final HttpSession session = request.getSession();
        session.setAttribute(OPENID_MODEL_KEY, aModel);
    }

    public OpenidModel getModel() {
        final HttpSession session = request.getSession();
        return (OpenidModel) session.getAttribute(OPENID_MODEL_KEY);
    }

    public String getRootUrl() {
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + "/" + ROOT_URL;
    }

    public String getReturnUrl() {
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + "/" + ROOT_URL;
    }

    public boolean isReturned() {
        final String queryString = this.request.getQueryString();

        LOGGER.debug("{}: Query string: {}", new Object[] {
                ErrorCodes.M_015_QUERY_STRING, queryString });

        if (queryString != null && !queryString.isEmpty()
                && request.getParameter("openid.mode") != null) {
            return true;
        }

        return false;
    }

    public void setConsumerManager(final ConsumerManager aConsumerManager) {
        request.getSession().setAttribute(OPENID_CONSUMER_MANAGER_KEY,
                aConsumerManager);
    }

    public ConsumerManager getConsumerManager() {
        return (ConsumerManager) request.getSession().getAttribute(
                OPENID_CONSUMER_MANAGER_KEY);
    }

    public void cleanIdentity() {
        request.getSession().invalidate();
    }

    public void setProxySettings(final ProxyProperties aProxyProperties) {
        ProxyProperties proxyProperties = aProxyProperties;
        if (proxyProperties == null) {
            proxyProperties = new ProxyProperties();
        }

        request.getSession().setAttribute(OPENID_PROXY_PROPERTIES,
                proxyProperties);
    }

    public ProxyProperties getProxySettings() {
        final Object obj =
                request.getSession().getAttribute(OPENID_PROXY_PROPERTIES);

        ProxyProperties proxyProperties = new ProxyProperties();
        if (obj == null) {
            final ServletContext context = request.getSession()
                    .getServletContext();
            @SuppressWarnings("unchecked")
            final Enumeration<String> enu = context.getInitParameterNames();

            while (enu.hasMoreElements()) {
                final String name = enu.nextElement();
                if (name.startsWith("proxy.")) {
                    if ("proxy.host".equals(name)) {
                        proxyProperties.setProxyHostName(context
                                .getInitParameter(name));
                    }
                    if ("proxy.port".equals(name)) {
                        proxyProperties.setProxyPort(Integer.valueOf(context
                                .getInitParameter(name)));
                    }
                    if ("proxy.user".equals(name)) {
                        proxyProperties.setUserName(context
                                .getInitParameter(name));
                    }
                    if ("proxy.password".equals(name)) {
                        proxyProperties.setPassword(context
                                .getInitParameter(name));
                    }
                    if ("proxy.domain".equals(name)) {
                        proxyProperties.setDomain(context
                                .getInitParameter(name));
                    }
                }
            }

            setProxySettings(proxyProperties);
        } else if (obj instanceof ProxyProperties) {
            proxyProperties = (ProxyProperties) obj;
        }

        return proxyProperties;
    }

    public OpenidService restoreService() {
        final OpenidService service = new OpenidService(getConsumerManager(),
                getDiscoveryInfo());
        final ProxyProperties props = getProxySettings();

        if (props.getProxyHostName() != null) {
            service.setProxyProperties(props);
        }

        return service;
    }

    public void saveService(final OpenidService aService) {
        this.setConsumerManager(aService.getConsumerManager());
        this.setDiscoveryInfo(aService.getDiscoveryInformation());
    }

    public boolean isRepaint() {
        if (request.getParameter(REPAINT_MESSAGE) != null) {
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getRequestParamsMap() {
        final Map<?, ?> map = request.getParameterMap();

        final Map<String, String> parametersMap = new HashMap<String, String>();

        for (final Object obj : map.entrySet()) {
            final Entry<String, String[]> e = (Entry<String, String[]>) obj;
            final String key = e.getKey();
            String value = null;

            final String[] params = (String[]) e.getValue();

            for (String s : params) {
                value = s;
                break;
            }

            parametersMap.put(key, value);
        }

        return parametersMap;
    }
}
