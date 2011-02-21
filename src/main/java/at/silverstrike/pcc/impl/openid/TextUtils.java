/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/
package at.silverstrike.pcc.impl.openid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.openid4java.util.ProxyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for converting different types to string representation.
 */
class TextUtils {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TextUtils.class);

    private TextUtils() {
    }

    /**
     * Creates URLencoded representation of Map object
     * 
     * @param aMap
     * @return
     */
    public static String mapToQueryString(final Map<?, ?> aMap) {
        final StringBuffer buf = new StringBuffer();

        try {
            for (final Entry<?, ?> curEntry : aMap.entrySet()) {
                buf.append(
                        URLEncoder
                                .encode(curEntry.getKey().toString(), "UTF-8"))
                        .append("=");
                buf.append(URLEncoder.encode(curEntry.getValue().toString(),
                        "UTF-8"));
                buf.append('&');
            }
        } catch (final UnsupportedEncodingException exception) {
            LOGGER.error(ErrorCodes.M_004_UNSUPPORTED_ENCODING, exception);
        }
        return buf.toString();

    }

    /**
     * Creates text representation of Map object
     * 
     * @param aMap
     * @return
     */
    public static String mapToString(final Map<?, ?> aMap) {
        final StringBuffer buf = new StringBuffer();

        for (final Entry<?, ?> curEntry : aMap.entrySet()) {

            buf.append(curEntry.getKey()).append("= [");
            buf.append(" ").append(curEntry.getValue()).append(" ");

            buf.append("]\n");
        }

        return buf.toString();
    }

    /**
     * Creates text representation of HttpSession object
     * 
     * @param aSession
     * @return
     */
    public static String sessionToString(final HttpSession aSession) {
        final StringBuffer buf = new StringBuffer();
        @SuppressWarnings("rawtypes")
        final Enumeration enu = aSession.getAttributeNames();

        while (enu.hasMoreElements()) {
            final String attr = (String) enu.nextElement();
            buf.append(attr).append("= [");
            buf.append(" ").append(aSession.getAttribute(attr)).append(" ");

            buf.append("]\n");
        }
        return buf.toString();
    }

    /**
     * Creates test representation of stack trace.
     * 
     * @param aException
     * @return
     */
    public static String makeTrace(final Exception aException) {
        final StringBuilder builder =
                new StringBuilder(aException.getClass().getCanonicalName());

        builder.append(":").append(aException.getMessage()).append("\n");

        for (StackTraceElement el : aException.getStackTrace()) {
            builder.append("at ").append(el.getClassName()).append("(")
                    .append(el.getMethodName()).append(":")
                    .append(el.getLineNumber()).append(")\n");
        }

        return builder.toString();
    }

    public static String proxyPropertiesToString(
            final ProxyProperties aProperties) {
        final StringBuilder builder = new StringBuilder();

        builder.append("Proxy properties: [\n").append("Host:")
                .append(aProperties.getProxyHostName()).
                append("\nPort:").append(aProperties.getProxyPort()).
                append("\nUser:").append(aProperties.getUserName()).
                append("\nPassword:").append(aProperties.getPassword()).
                append("\nDomain:").append(aProperties.getDomain())
                .append("\n]");

        return builder.toString();
    }
}
