/*
 * Copyright (C) Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package sites;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.common.base.Strings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class SitesConfigHelper {
    private static final Logger logger = Logger.getLogger(SitesConfigHelper.class.getName());

    private static final String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String SITES_SCOPE = "https://sites.google.com/feeds/";

    private static final String GLIGHT_CONFIG_DIR;
    private static final String RESOURCE_OWNER_CREDENTIAL_FILE;

    private static final String SITES_RESOURCE_CONSUMER_CLIENT_ID;
    private static final String SITES_RESOURCE_CONSUMER_CLIENT_SECRET;

    private static String SITES_RESOURCE_OWNER_ACCESS_TOKEN;
    private static String SITES_RESOURCE_OWNER_REFRESH_TOKEN;
    private static Long SITES_RESOURCE_OWNER_ACCESS_TOKEN_EXPIRTY_TIME;

    static {
        // First check that config dir exists.
        GLIGHT_CONFIG_DIR = System.getProperty("user.home") + "/" + "glightconfig";
        File glightConfigDir = new File(GLIGHT_CONFIG_DIR);
        if (!glightConfigDir.exists()) {
            createConfigDirectory(GLIGHT_CONFIG_DIR);
        }

        // Second, check that resource-consumer-credential file exists as this is required
        // to obtain credentials for the resource-owners.
        String resourceConsumerCredentialFilePath =
                GLIGHT_CONFIG_DIR + "/" + "resource_consumer_credentials";
        File resourceConsumerCredentialFile = new File(resourceConsumerCredentialFilePath);
        if (!resourceConsumerCredentialFile.exists()) {
            logger.info("Populate " + resourceConsumerCredentialFilePath
                    + " with resource-consumer-credentials and restart.");
            System.exit(1);
        }
        Properties resourceConsumerCredentials =
                loadPropertiesFile(resourceConsumerCredentialFilePath);
        SITES_RESOURCE_CONSUMER_CLIENT_ID =
                (String) resourceConsumerCredentials
                        .get(CredentialKeys.SITES_RESOURCE_CONSUMER_CLIENT_ID.getKey());
        checkArgument(!Strings.isNullOrEmpty(SITES_RESOURCE_CONSUMER_CLIENT_ID));

        SITES_RESOURCE_CONSUMER_CLIENT_SECRET =
                (String) resourceConsumerCredentials
                        .get(CredentialKeys.SITES_RESOURCE_CONSUMER_CLIENT_SECRET.getKey());
        checkArgument(!Strings.isNullOrEmpty(SITES_RESOURCE_CONSUMER_CLIENT_SECRET));

        // Third, validate resource-owner-credentials.
        RESOURCE_OWNER_CREDENTIAL_FILE = GLIGHT_CONFIG_DIR + "/" + "resource_owner_credentials";
        File resourceOwnerCredentialFile = new File(RESOURCE_OWNER_CREDENTIAL_FILE);

        if (!resourceOwnerCredentialFile.exists()) {
            recreateResourceOwnerConfig();
        }

        Properties resourceOwnerCredentials = loadPropertiesFile(RESOURCE_OWNER_CREDENTIAL_FILE);
        SITES_RESOURCE_OWNER_ACCESS_TOKEN =
                resourceOwnerCredentials.getProperty(CredentialKeys.SITES_ACCESS_TOKEN.getKey());
        checkArgument(!Strings.isNullOrEmpty(SITES_RESOURCE_OWNER_ACCESS_TOKEN));

        SITES_RESOURCE_OWNER_REFRESH_TOKEN =
                resourceOwnerCredentials.getProperty(CredentialKeys.SITES_REFRESH_TOKEN.getKey());
        checkArgument(!Strings.isNullOrEmpty(SITES_RESOURCE_OWNER_REFRESH_TOKEN));

        SITES_RESOURCE_OWNER_ACCESS_TOKEN_EXPIRTY_TIME =
                Long.parseLong(resourceOwnerCredentials
                        .getProperty(CredentialKeys.SITES_ACCESS_TOKEN_EXPIRY_TIME
                                .getKey()));
    }

    private static Properties loadPropertiesFile(String absPathToPropertyFile) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(absPathToPropertyFile));
            return properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createConfigDirectory(String absPathToDir) {
        createFileIfNotExists(absPathToDir, true /* isdir */);
    }

    public static void createFile(String absPathToFile) throws IOException {
        createFileIfNotExists(absPathToFile, false);
    }

    public static void removeFile(String absPathToFile) {
        File file = new File(absPathToFile);

        if (file.isDirectory()) {
            throw new RuntimeException("Directory cannot be removed from this method.");
        } else if (file.exists()) {
            file.delete();
        }
    }

    private static void createFileIfNotExists(String absPathToFile, boolean isDir)
    {
        try {
            File file = new File(absPathToFile);

            if (file.exists()) {
                file.delete();
            }

            boolean success = false;

            if (isDir) {
                success = file.mkdirs();
                Runtime.getRuntime().exec("chmod 700 " + file.getAbsolutePath());
            } else {
                success = file.createNewFile();
                Runtime.getRuntime().exec("chmod 600 " + file.getAbsolutePath());
            }

            if (success == false) {
                throw new RuntimeException("Failed to create File[" + absPathToFile
                        + "] with isDir="
                        + isDir + "].");
            }

            logger.info("Successfully created [" + file.getAbsolutePath() + "].");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void recreateResourceOwnerConfig() {
        try {
            createFile(RESOURCE_OWNER_CREDENTIAL_FILE);
            generateNewAccessTokenForResourceOwner(RESOURCE_OWNER_CREDENTIAL_FILE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO(arjuns) : This method needs more attention.
    private void refreshAccessTokenForResourceOwner() {
        try {
            // Refresh access-token using refresh-token.
            GoogleAccessProtectedResource access =
                    new GoogleAccessProtectedResource(SITES_RESOURCE_OWNER_ACCESS_TOKEN,
                            TRANSPORT, JSON_FACTORY, SITES_RESOURCE_CONSUMER_CLIENT_ID,
                            SITES_RESOURCE_CONSUMER_CLIENT_SECRET,
                            SITES_RESOURCE_OWNER_REFRESH_TOKEN);
            HttpRequestFactory rf = TRANSPORT.createRequestFactory(access);

            String oldAccessToken = SITES_RESOURCE_OWNER_ACCESS_TOKEN;
            access.refreshToken();
            logger.info("Old AccessToken = " + oldAccessToken + ", New AccessToken = "
                    + access.getAccessToken());
            logger.info("Old refreshToken = " + SITES_RESOURCE_OWNER_REFRESH_TOKEN
                    + ", New RefreshToken = " + access.getRefreshToken());

            // TODO(arjuns) : See how much time it remains valid. 3600 is pulled out of air.
            generateResourceOwnerCredentialFile(access.getAccessToken(),
                    SITES_RESOURCE_OWNER_REFRESH_TOKEN,
                    3600L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateNewAccessTokenForResourceOwner(String sitesConfigFile)
            throws FileNotFoundException,
            IOException {
        // Generate the URL to which we will direct users
        String authorizeUrl = new GoogleAuthorizationRequestUrl(
                SITES_RESOURCE_CONSUMER_CLIENT_ID, CALLBACK_URL, SITES_SCOPE).build();
        System.out.println("Paste this url in your browser: \n" + authorizeUrl);

        // Wait for the authorization code
        System.out.println("Type the code you received here: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String authorizationCode = in.readLine();

        // Exchange for an access and refresh token
        GoogleAuthorizationCodeGrant authRequest =
                new GoogleAuthorizationCodeGrant(TRANSPORT,
                        JSON_FACTORY, SITES_RESOURCE_CONSUMER_CLIENT_ID,
                        SITES_RESOURCE_CONSUMER_CLIENT_SECRET, authorizationCode, CALLBACK_URL);

        authRequest.useBasicAuthorization = false;
        AccessTokenResponse authResponse = authRequest.execute();
        generateResourceOwnerCredentialFile(authResponse.accessToken, authResponse.refreshToken,
                authResponse.expiresIn);
    }

    private static void generateResourceOwnerCredentialFile(String accessToken,
            String refreshToken, Long expiryInSecFromNow) throws IOException, FileNotFoundException {
        checkArgument(!Strings.isNullOrEmpty(accessToken));
        checkArgument(!Strings.isNullOrEmpty(refreshToken));

        // TODO(arjuns) : Move this to a common place.
        Long validBefore = System.currentTimeMillis() + (expiryInSecFromNow - 30) * 1000;

        // Delete resource-owner-credential file.
        removeFile(RESOURCE_OWNER_CREDENTIAL_FILE);

        // TODO(arjuns) : later replace this with json.
        Properties props = new Properties();
        props.setProperty(CredentialKeys.SITES_ACCESS_TOKEN.getKey(), accessToken);
        props.setProperty(CredentialKeys.SITES_REFRESH_TOKEN.getKey(), refreshToken);
        props.setProperty(CredentialKeys.SITES_ACCESS_TOKEN_EXPIRY_TIME.getKey(),
                Long.toString(validBefore));
        props.store(new FileOutputStream(new File(RESOURCE_OWNER_CREDENTIAL_FILE)),
                "Resource Owner Credentials for UnitTesting.");

        SITES_RESOURCE_OWNER_ACCESS_TOKEN = accessToken;
        SITES_RESOURCE_OWNER_REFRESH_TOKEN = refreshToken;
        SITES_RESOURCE_OWNER_ACCESS_TOKEN_EXPIRTY_TIME = validBefore;
    }

    public static enum CredentialKeys {
        // Keys for Resource-owner-credential-file
        SITES_ACCESS_TOKEN("sites_resource_owner_access_token"),
        SITES_REFRESH_TOKEN("sites_resource_owner_refresh_token"),
        SITES_ACCESS_TOKEN_EXPIRY_TIME("sites_resource_owner_access_token_expiry_time"),

        // Keys for Resource-consumer-credential-file
        SITES_RESOURCE_CONSUMER_CLIENT_ID("sites_resource_consumer_client_id"),
        SITES_RESOURCE_CONSUMER_CLIENT_SECRET("sites_resource_consumer_client_secret");

        private String key;

        private CredentialKeys(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public static String getAccessToken() {
        return SITES_RESOURCE_OWNER_ACCESS_TOKEN;
    }
}
