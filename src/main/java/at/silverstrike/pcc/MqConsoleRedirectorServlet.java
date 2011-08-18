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

package at.silverstrike.pcc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DP118M
 * 
 */
public class MqConsoleRedirectorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MqConsoleRedirectorServlet.class);

    @Override
    protected void service(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse)
            throws ServletException, IOException {

        aRequest.getRequestDispatcher("http://78.47.242.60:8161/admin")
                .forward(aRequest, aResponse);
    }

}
