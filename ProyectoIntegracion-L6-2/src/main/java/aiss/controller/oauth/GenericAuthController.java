package aiss.controller.oauth;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;

import aiss.utility.OAuthRegistry;

/**
 * Generic OAuth controller that initiates an OAuth 2.0 authorization flow.
 *
 * This servlet uses two init parameters:
 * <ul>
 * <li><b></b>: This <i>required</i></li>
 * <li><b></b>: This <i>optional</i> parameter specifies the</li>
 * <ul>
 */
public class GenericAuthController extends AbstractAuthorizationCodeServlet {

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(GenericAuthController.class.getName());

    /**
     * @param request
     * @param response
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String provider = getInitParameter("provider");
        if (provider == null || "".equals(provider)) {
            log.warning("No provider found in OAuth Callbak servlet for request: " + request.getRequestURI());
        } else {
            request.getSession().setAttribute(provider + "-token", getCredential().getAccessToken());
            OAuthRegistry.onAuthorizationSuccess(getInitParameter("onSuccess"), provider, getCredential(), request, response);
        }
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        String provider = getInitParameter("provider");
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath("/oauth2callback/" + provider);
        return url.build();
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        return OAuthRegistry.initializeFlow(getInitParameter("provider"));
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
        return UUID.randomUUID().toString();
    }

}
