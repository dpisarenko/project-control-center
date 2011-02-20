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

final class HttpProcessor {

    private final HttpServletRequest request;
    private final static String OPENID_DISCOVERY_KEY = "openid.discovery";
    private final static String OPENID_MODEL_KEY = "openid.model";
    private final static String OPENID_CONSUMER_MANAGER_KEY = "openid.consumer-manager";
    private final static String OPENID_PROXY_PROPERTIES = "openid.proxy-properties";
    private final static String ROOT_URL = "openid-sample";
    private final static String REPAINT_MESSAGE = "repaintAll";

    /**
     * 
     * @param request
     */
    public HttpProcessor(HttpServletRequest request) {
        this.request = request;
    }

    public void setDiscoveryInfo(DiscoveryInformation info) {
        HttpSession session = request.getSession();
        session.setAttribute(OPENID_DISCOVERY_KEY, info);
    }

    public DiscoveryInformation getDiscoveryInfo() {
        HttpSession session = request.getSession();
        return (DiscoveryInformation) session.getAttribute(OPENID_DISCOVERY_KEY);
    }

    public void setModel(OpenidModel model) {
        HttpSession session = request.getSession();
        session.setAttribute(OPENID_MODEL_KEY, model);
    }

    public OpenidModel getModel() {
        HttpSession session = request.getSession();
        return (OpenidModel) session.getAttribute(OPENID_MODEL_KEY);
    }

    public String getRootUrl() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + ROOT_URL;
    }

    public String getReturnUrl() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + ROOT_URL;
    }

    public boolean isReturned() {

        String queryString = this.request.getQueryString();
        if (queryString != null && !queryString.isEmpty() && request.getParameter("openid.mode") != null) {
            return true;
        }

        return false;
    }

    public void setConsumerManager(ConsumerManager consumerManager) {
        request.getSession().setAttribute(OPENID_CONSUMER_MANAGER_KEY, consumerManager);
    }

    public ConsumerManager getConsumerManager() {
        return (ConsumerManager) request.getSession().getAttribute(OPENID_CONSUMER_MANAGER_KEY);
    }

    public void cleanIdentity() {
        request.getSession().invalidate();
    }

    public void setProxySettings(ProxyProperties proxyProperties) {
        if (proxyProperties == null) {
            proxyProperties = new ProxyProperties();
        }

        request.getSession().setAttribute(OPENID_PROXY_PROPERTIES, proxyProperties);
    }

    public ProxyProperties getProxySettings() {
        Object obj = request.getSession().getAttribute(OPENID_PROXY_PROPERTIES);

        ProxyProperties proxyProperties = new ProxyProperties();
        if (obj == null) {
            ServletContext context = request.getSession().getServletContext();
            @SuppressWarnings("unchecked")
			Enumeration<String> enu = context.getInitParameterNames();

            while (enu.hasMoreElements()) {
                String name = enu.nextElement();
                if (name.startsWith("proxy.")) {
                    if ("proxy.host".equals(name)) {
                        proxyProperties.setProxyHostName(context.getInitParameter(name));
                    }
                    if ("proxy.port".equals(name)) {
                        proxyProperties.setProxyPort(Integer.valueOf(context.getInitParameter(name)));
                    }
                    if ("proxy.user".equals(name)) {
                        proxyProperties.setUserName(context.getInitParameter(name));
                    }
                    if ("proxy.password".equals(name)) {
                        proxyProperties.setPassword(context.getInitParameter(name));
                    }
                    if ("proxy.domain".equals(name)) {
                        proxyProperties.setDomain(context.getInitParameter(name));
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
        OpenidService service = new OpenidService(getConsumerManager(), getDiscoveryInfo());
        ProxyProperties props = getProxySettings();

        if (props.getProxyHostName() != null) {
            service.setProxyProperties(props);
        }

        return service;
    }

    public void saveService(OpenidService service) {
        this.setConsumerManager(service.getConsumerManager());
        this.setDiscoveryInfo(service.getDiscoveryInformation());
    }

    public boolean isRepaint() {
        if (request.getParameter(REPAINT_MESSAGE) != null) {
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getRequestParamsMap() {
        Map<?, ?> map = request.getParameterMap();

        Map<String, String> parametersMap = new HashMap<String, String>();

        for (Object obj : map.entrySet()) {
            Entry<String, String[]> e = (Entry<String, String[]>) obj;
            String key = e.getKey();
            String value = null;

            String[] params = (String[]) e.getValue();

            for (String s : params) {
                value = s;
                break;
            }

            parametersMap.put(key, value);
        }

        return parametersMap;
    }
}
