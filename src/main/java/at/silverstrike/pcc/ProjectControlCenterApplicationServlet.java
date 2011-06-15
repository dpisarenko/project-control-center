package at.silverstrike.pcc;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.terminal.gwt.server.ApplicationServlet;

public class ProjectControlCenterApplicationServlet extends ApplicationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void writeAjaxPageHtmlHeader(BufferedWriter page, String title,
            String themeUri, HttpServletRequest request) throws IOException {
        super.writeAjaxPageHtmlHeader(page, title, themeUri, request);
        page.write("<meta name=\"google-site-verification\" content=\"9MkRA85NNld5m2iBT4PuHMAbztx02ba8yoBwNTm8d3Q\" />\n");
    }

}
