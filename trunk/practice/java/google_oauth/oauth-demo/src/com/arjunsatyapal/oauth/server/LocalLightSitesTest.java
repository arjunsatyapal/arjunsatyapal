package com.arjunsatyapal.oauth.server;

import com.google.gdata.data.sites.SiteEntry;
import com.google.gdata.data.sites.SiteFeed;
import com.google.gdata.util.ServiceException;
import java.net.URL;

import com.google.gdata.client.sites.SitesService;

import java.io.InputStream;

import java.util.logging.LogManager;

import java.util.logging.Logger;

import java.util.logging.Level;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalLightSitesTest {
    private static Logger logger = Logger.getLogger(LocalLightSitesTest.class.getName());
    private static final String SCOPE = "https://sites.google.com/feeds/";
    private static final String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    // FILL THESE IN WITH YOUR VALUES FROM THE API CONSOLE
    private static final String CLIENT_ID = "623685080366.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "CP2nvtLuyM2g87P9KI0e9JgQ";
    // private static final String APPLICATION_NAME = "google-arjuns-corp";

    public static final String APP_NAME = "google-JavaSitesClientSample-v1.1";

    private static final SitesService service = new SitesService(APP_NAME);

    public static void main(String[] args) throws IOException, SecurityException, ServiceException {

        LocalLightSitesTest object = new LocalLightSitesTest();
        object.run();
    }

    public void run() throws SecurityException, IOException, ServiceException {
        InputStream ins =
                ClassLoader.getSystemClassLoader().getResourceAsStream("logging.properties");
        LogManager.getLogManager().readConfiguration(ins);
        logger.isLoggable(Level.CONFIG);

        // Generate the URL to which we will direct users

        String authorizeUrl = new GoogleAuthorizationRequestUrl(CLIENT_ID,
                CALLBACK_URL, SCOPE).build();
        System.out.println("Paste this url in your browser: \n" + authorizeUrl);

        // Wait for the authorization code
        System.out.println("Type the code you received here: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String authorizationCode = in.readLine();

        StringBuilder stringBuilder = new StringBuilder();

        // Exchange for an access and refresh token
        GoogleAuthorizationCodeGrant authRequest = new GoogleAuthorizationCodeGrant(TRANSPORT,
                JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, authorizationCode, CALLBACK_URL);
        authRequest.useBasicAuthorization = false;
        AccessTokenResponse authResponse = authRequest.execute();
        System.out.println(authRequest.toString());
        String accessToken = authResponse.accessToken;
        GoogleAccessProtectedResource access = new GoogleAccessProtectedResource(accessToken,
                TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, authResponse.refreshToken);
        TRANSPORT.createRequestFactory(access);
        stringBuilder.append("Access token: " + authResponse.accessToken);

        // Make an authenticated request
        service.setAuthSubToken(accessToken);

        System.out.println(stringBuilder.toString());

        getSiteFeed(null);

    }

    public void getSiteFeed(String domain) throws IOException, ServiceException {
        SiteFeed siteFeed = service.getFeed(
                new URL(getSiteFeedUrl(domain)), SiteFeed.class);
        for (SiteEntry entry : siteFeed.getEntries()) {
            System.out.println("title: " + entry.getTitle().getPlainText());
            System.out.println("site name: " + entry.getSiteName().getValue());
            System.out.println("theme: " + entry.getTheme().getValue());
            System.out.println("");
        }
    }

    public String getSiteFeedUrl(final String domain) {
        String requiredDomain = domain == null ? "site" : domain;
        return "https://sites.google.com/feeds/site/" + requiredDomain + "/";
    }
}
