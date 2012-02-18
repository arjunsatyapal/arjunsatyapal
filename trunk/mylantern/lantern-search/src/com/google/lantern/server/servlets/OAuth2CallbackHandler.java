package com.google.lantern.server.servlets;

import static com.google.lantern.server.utils.oauth2.OAuth2Utils.getOAuth2CallbackUrl;
import static com.google.lantern.server.utils.oauth2.ResourceConsumerCredentials.getClientId;
import static com.google.lantern.server.utils.oauth2.ResourceConsumerCredentials.getClientSecret;

import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.common.base.Strings;
import com.google.lantern.server.persistence.dao.UserDao;
import com.google.lantern.server.persistence.entities.UserEntity;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.server.utils.oauth2.AccessTokenType;
import com.google.lantern.server.utils.sites.SitesUrl;
import com.google.lantern.shared.ServletPaths;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP servlet to process access granted from user.
 * 
 * @author Yaniv Inbar
 */
@SuppressWarnings("serial")
public class OAuth2CallbackHandler extends HttpServlet {
    private static final String PARAM_AUTHORIZATION_CODE = "code";
    private static final String PARAM_ERROR = "error";

    private static final HttpTransport TRANSPORT = AppEngineUtils.getHttpTransport();
    private static final JsonFactory JSON_FACTORY = AppEngineUtils.getJsonFactory();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Parse the token that will be used to look up the flow object
            String authorizationCode = req.getParameter(PARAM_AUTHORIZATION_CODE);
            String errorCode = req.getParameter(PARAM_ERROR);

            if (Strings.isNullOrEmpty(authorizationCode) && Strings.isNullOrEmpty(errorCode)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print("Must have a query parameter: " + PARAM_AUTHORIZATION_CODE);
                return;
            } else if (!Strings.isNullOrEmpty(errorCode)) {
                resp.sendRedirect(ServletPaths.JavaServlets.OAUTH_2_DENIED_SERVLET);
                return;
            }

            // Exchange for an access and refresh token
            AuthorizationCodeGrant request = new AuthorizationCodeGrant(TRANSPORT,
                    JSON_FACTORY,
                    GoogleAccessTokenRequest.AUTHORIZATION_SERVER_URL,
                    getClientId(),
                    getClientSecret(),
                    authorizationCode,
                    getOAuth2CallbackUrl(req));
            AccessTokenResponse response = request.execute();

            UserEntity userEntity =
                    new UserEntity(AppEngineUtils.getUser(), AccessTokenType.BEARER,
                            response.accessToken, response.refreshToken, response.expiresIn,
                            SitesUrl.getRootUrl(), getOAuth2CallbackUrl(req));
            UserDao.addNewUser(userEntity);

            if (AppEngineUtils.isDevServer()) {
                resp.sendRedirect(ServletPaths.JavaServlets.HOME_DEV_SERVER);
            } else {
                resp.sendRedirect(ServletPaths.JavaServlets.HOME);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
