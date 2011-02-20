package at.silverstrike.pcc.impl.openid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.openid4java.util.ProxyProperties;

/**
 * Class for converting different types to string representation.
 */
class TextUtils {

    /**
     * Creates URLencoded representation of Map object
     * 
     * @param map
     * @return
     */
    public static String mapToQueryString(Map<?, ?> map) {
        StringBuffer buf = new StringBuffer();

        try {
            for (Entry<?, ?> e : map.entrySet()) {
                buf.append(URLEncoder.encode(e.getKey().toString(), "UTF-8")).append("=");
                buf.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
                buf.append('&');
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buf.toString();

    }

    /**
     * Creates text representation of Map object
     * 
     * @param map
     * @return
     */
    public static String mapToString(Map<?, ?> map) {
        StringBuffer buf = new StringBuffer();

        for (Entry<?, ?> e : map.entrySet()) {

            buf.append(e.getKey()).append("= [");
            buf.append(" ").append(e.getValue()).append(" ");

            buf.append("]\n");
        }

        return buf.toString();
    }

    /**
     * Creates text representation of HttpSession object
     * 
     * @param session
     * @return
     */
    public static String sessionToString(HttpSession session) {
        StringBuffer buf = new StringBuffer();
        @SuppressWarnings("rawtypes")
		Enumeration enu = session.getAttributeNames();

        while (enu.hasMoreElements()) {
            String attr = (String) enu.nextElement();
            buf.append(attr).append("= [");
            buf.append(" ").append(session.getAttribute(attr)).append(" ");

            buf.append("]\n");
        }
        return buf.toString();
    }

    /**
     * Creates test representation of stack trace.
     * 
     * @param e
     * @return
     */
    public static String makeTrace(Exception e) {
        StringBuilder builder = new StringBuilder(e.getClass().getCanonicalName());

        builder.append(":").append(e.getMessage()).append("\n");

        for (StackTraceElement el : e.getStackTrace()) {
            builder.append("at ").append(el.getClassName()).append("(").append(el.getMethodName()).append(":")
                    .append(el.getLineNumber()).append(")\n");
        }

        return builder.toString();
    }


    public static String proxyPropertiesToString(ProxyProperties props) {
        StringBuilder builder = new StringBuilder();

        builder.append("Proxy properties: [\n").append("Host:").append(props.getProxyHostName()).
                append("\nPort:").append(props.getProxyPort()).
                append("\nUser:").append(props.getUserName()).
                append("\nPassword:").append(props.getPassword()).
                append("\nDomain:").append(props.getDomain()).append("\n]");

        return builder.toString();
    }
}
