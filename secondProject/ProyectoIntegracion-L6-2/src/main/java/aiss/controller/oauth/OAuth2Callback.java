package aiss.controller.oauth;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;

import aiss.utility.CustomTokenResponse;
import aiss.utility.OAuthRegistry;

/**
 * Servlet implementation class OAuth2Callback
 */
public class OAuth2Callback extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(OAuth2Callback.class.getName());

	/** Lock on the flow. */
	private final Lock lock = new ReentrantLock();

	/**
	 * Authorization code flow to be used across all HTTP servlet requests or
	 * {@code null} before initialized in {@link #initializeFlow()}.
	 */
	private AuthorizationCodeFlow flow;

	protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer buf = req.getRequestURL();
		if (req.getQueryString() != null) {
			buf.append('?').append(req.getQueryString());
		}
		AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
		String code = responseUrl.getCode();
		if (responseUrl.getError() != null) {
			onError(req, resp, responseUrl);
		} else if (code == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().print("Missing authorization code");
		} else {
			lock.lock();
			try {
				if (flow == null) {
					flow = initializeFlow();
				}
				String redirectUri = getRedirectUri(req);
				TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri)
						.setResponseClass(CustomTokenResponse.class).execute();
				String userId = getUserId(req);
				Credential credential = flow.createAndStoreCredential(response, userId);
				onSuccess(req, resp, credential);
			} finally {
				lock.unlock();
			}
		}
	}

    
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential) throws ServletException, IOException {
        String provider = getInitParameter("provider");
        if (provider == null || "".equals(provider)) {
            log.warning("No provider found in OAuth Callbak servlet for request: " + req.getRequestURI());
        } else {
            req.getSession().setAttribute(provider + "-token", credential.getAccessToken());
            OAuthRegistry.onAuthorizationSuccess(getInitParameter("onSuccess"), provider, credential, req, resp);
        }
    }

    
    protected void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse) throws ServletException, IOException {
        log.warning("ERROR on OAuth Callback! " + errorResponse.getCode() + ": " + errorResponse.getError() + ". " + errorResponse.getErrorDescription());
        resp.getWriter().append("ERROR!").append(errorResponse.getCode()).append(": ").append(errorResponse.getError()).append(errorResponse.getErrorDescription());
    }

    
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {

        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath("/oauth2callback/" + getInitParameter("provider"));
        return url.build();
    }

    
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        return OAuthRegistry.initializeFlow(getInitParameter("provider"));
    }

    
    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
        return UUID.randomUUID().toString();
    }
}
