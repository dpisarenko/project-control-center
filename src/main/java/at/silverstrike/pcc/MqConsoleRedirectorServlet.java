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

/**
 * @author DP118M
 * 
 */
public class MqConsoleRedirectorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse)
            throws ServletException, IOException {

        final RequestDispatcher requestDispatcher =
                getServletContext().getRequestDispatcher(
                        "http://localhost:8161/admin");
        requestDispatcher.forward(aRequest, aResponse);
    }

}
