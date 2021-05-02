package aiss.utility;

import com.google.api.client.auth.oauth2.RefreshTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;

/**
 * OAuth 2.0 JSON model for a successful access token response as specified in
 * <a href="http://tools.ietf.org/html/rfc6749#section-5.1">Successful
 * Response</a>.
 *
 * Sometimes, servers do not send rfc6749-compliant JSON models, so 
 * it becomes necessary perform some changes and transformations.
 * For instance, if "scopes" is returned as a List<String> instead of
 * a String, you would need to perform the following changes:
 * 
 * -------
 * private List<String> scope;
 * -------
 * public String getScope() {
 *   return String.join(",",  scope);
 * }
 * -------
 * public CustomTokenResponse setScope(List<String> scope) {
 *   this.scope = scope;
 *   return this;
 * }
 * -------
 *
 */
public class CustomTokenResponse extends TokenResponse {

	/** Access token issued by the authorization server. */
	@Key("access_token")
	private String accessToken;

	/**
	 * Token type (as specified in
	 * <a href="http://tools.ietf.org/html/rfc6749#section-7.1">Access Token
	 * Types</a>).
	 */
	@Key("token_type")
	private String tokenType;

	/**
	 * Lifetime in seconds of the access token (for example 3600 for an hour) or
	 * {@code null} for none.
	 */
	@Key("expires_in")
	private Long expiresInSeconds;

	/**
	 * Refresh token which can be used to obtain new access tokens using
	 * {@link RefreshTokenRequest} or {@code null} for none.
	 */
	@Key("refresh_token")
	private String refreshToken;

	/**
	 * Scope of the access token as specified in
	 * <a href="http://tools.ietf.org/html/rfc6749#section-3.3">Access Token
	 * Scope</a> or {@code null} for none.
	 */
	@Key("scope")
	private String scope;

	/** Returns the access token issued by the authorization server. */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token issued by the authorization server.
	 *
	 * <p>
	 * Overriding is only supported for the purpose of calling the super
	 * implementation and changing the return type, but nothing else.
	 * </p>
	 */
	public CustomTokenResponse setAccessToken(String accessToken) {
		this.accessToken = Preconditions.checkNotNull(accessToken);
		return this;
	}

	/**
	 * Returns the token type (as specified in
	 * <a href="http://tools.ietf.org/html/rfc6749#section-7.1">Access Token
	 * Types</a>).
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Sets the token type (as specified in
	 * <a href="http://tools.ietf.org/html/rfc6749#section-7.1">Access Token
	 * Types</a>).
	 *
	 * <p>
	 * Overriding is only supported for the purpose of calling the super
	 * implementation and changing the return type, but nothing else.
	 * </p>
	 */
	public CustomTokenResponse setTokenType(String tokenType) {
		this.tokenType = Preconditions.checkNotNull(tokenType);
		return this;
	}

	/**
	 * Returns the lifetime in seconds of the access token (for example 3600 for an
	 * hour) or {@code null} for none.
	 */
	public Long getExpiresInSeconds() {
		return expiresInSeconds;
	}

	/**
	 * Sets the lifetime in seconds of the access token (for example 3600 for an
	 * hour) or {@code null} for none.
	 *
	 * <p>
	 * Overriding is only supported for the purpose of calling the super
	 * implementation and changing the return type, but nothing else.
	 * </p>
	 */
	public CustomTokenResponse setExpiresInSeconds(Long expiresInSeconds) {
		this.expiresInSeconds = expiresInSeconds;
		return this;
	}

	/**
	 * Returns the refresh token which can be used to obtain new access tokens using
	 * the same authorization grant or {@code null} for none.
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token which can be used to obtain new access tokens using
	 * the same authorization grant or {@code null} for none.
	 *
	 * <p>
	 * Overriding is only supported for the purpose of calling the super
	 * implementation and changing the return type, but nothing else.
	 * </p>
	 */
	public CustomTokenResponse setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	/**
	 * Returns the scope of the access token or {@code null} for none.
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * Sets the scope of the access token or {@code null} for none.
	 *
	 * <p>
	 * Overriding is only supported for the purpose of calling the super
	 * implementation and changing the return type, but nothing else.
	 * </p>
	 */
	public CustomTokenResponse setScope(String scope) {
		this.scope = scope;
		return this;
	}

	@Override
	public CustomTokenResponse set(String fieldName, Object value) {
		return (CustomTokenResponse) super.set(fieldName, value);
	}

	@Override
	public CustomTokenResponse clone() {
		return (CustomTokenResponse) super.clone();
	}
}
