/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sites;

import com.google.api.client.http.HttpHeaders;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.data.sites.AttachmentEntry;
import com.google.gdata.data.sites.BaseContentEntry;
import com.google.gdata.data.sites.SiteEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.InvalidEntryException;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.VersionConflictException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import util.SimpleCommandLineParser;

/**
 * Sample command-line application that demonstrates the major functionality of the Google Sites
 * Data API.
 */
public class SitesDemo {
    private SitesHelper sitesHelper;

    public static final String APP_NAME = "google-JavaSitesClientSample-v1.1";

    /**
     * The message for displaying the usage parameters.
     */
    private static final String[] USAGE_MESSAGE =
            {
                            "Usage: java SitesDemo.jar --siteName <webspace name of Site> --username <email> "
                                    + "--password <pass>",
                            "Usage: java SitesDemo.jar  --siteName <webspace name of Site> --token <AuthSub token>",
                            "    [--domain]  Google Apps domain (e.g. example.com)",
                            "    [--log]  Enable request logging tot stderr",
                            ""
            };

    /**
     * Welcome message.
     */
    private static final String[] WELCOME_MESSAGE = {
                    "", "--Google Sites Data API Java Client Demo--",
                    "This app lets you fetch, upload, and download content to/from Google Sites.",
                    "Type 'help' for a list of commands.", ""
    };

    /**
     * Help on all available commands.
     */
    private static final String[] COMMAND_HELP_MESSAGE =
            {
                            "Commands:",
                            "    sites                                             [[lists the user's sites]]",
                            "    newsite <title> <description> [theme]             [[creates a new Google Site]]",
                            "    copysite <title> <description> <source site>      [[copies an existing Google Site]]",
                            "    content [all|kind|kind1,kind2,kind3,...]          [[lists the Site content]]",
                            "    activity                                          [[lists recent Site activity]]",
                            "    create <kind> <title> [parentEntryId]             [[create a new page]]",
                            "    delete <entryId>                                  [[delete page/item]]",
                            "    upload <file_path> <parentEntryId> [description]  [[uploads an attachment to a parent "
                                    + "page/filecabinet]]",
                            "    download <entryId|\"all\"> <file_path>            [[downloads an attachment or all the "
                                    + "Site's attachments to the folder "
                                    + "specified by file_path]]",
                            "    revisions <entryId>                               [[lists revisions of an page/item]]",
                            "    acls <siteName>                                   [[lists the sharing permissions "
                                    + "for a site]]",
                            "",
                            "    kinds                                             [[lists possible values for page "
                                    + "kinds]]",
                            "    help                                              [[display this message, or info "
                                    + "about the specified command]]",
                            "    exit                                              [[exit the program]]"
            };

    /**
     * Constructor
     * 
     * @param appName Name of the application.
     * @param domain The Google Apps domain of the hosted Site or "site".
     * @param siteName The webspace name of the Site.
     * @param username User's email address.
     * @param password User's password.
     * @param enableLogging Whether to enable HTTP/XML logging.
     * @throws AuthenticationException
     */
    public SitesDemo(String appName, String domain, String siteName, String username,
            String password, boolean enableLogging) throws AuthenticationException {
        sitesHelper = new SitesHelper(appName, domain, siteName, enableLogging);
        sitesHelper.login(username, password);
    }

    /**
     * Constructor
     * 
     * @param appName Name of the application.
     * @param domain The Google Apps domain of the hosted Site or "site".
     * @param siteName The webspace name of the Site.
     * @param authSubToken User's AuthSub session token.
     * @param enableLogging Whether to enable HTTP/XML logging.
     */
    
    private String token;
    public SitesDemo(String appName, String domain, String siteName, String authSubToken,
            boolean enableLogging) {
        sitesHelper = new SitesHelper(appName, domain, siteName, enableLogging);
        this.token = authSubToken;
//        sitesHelper.login(authSubToken);
    }

    /**
     * Prints out a given message.
     * 
     * @param msg the message to be printed.
     */
    private static void printMessage(String[] msg) {
        printMessage(msg, true);
    }

    /**
     * Prints out a given message.
     * 
     * @param msg the message to be printed.
     * @param addNewline Whether to put a carriage return after every value.
     */
    private static void printMessage(String[] msg, boolean addNewline) {
        for (String s : msg) {
            if (addNewline) {
                System.out.println(s);
            } else {
                System.out.print(s + " ");
            }
        }
    }

    /**
     * Prints out the supported page kinds.
     */
    private void listSupportedKinds() {
        System.out.print("\nSupported kind values: ");
        printMessage(sitesHelper.KINDS, false);
        System.out.println();
    }

    /**
     * Reads and executes one command.
     * 
     * @param reader to read input from the keyboard
     * @return false if the user quits, true on exception
     * @throws IOException
     * @throws ServiceException
     * @throws SitesException
     */
    private boolean executeCommand(BufferedReader reader) throws IOException, ServiceException,
            SitesException {
        System.out.print("Command: ");

        String[] args = parseCommand(reader.readLine());
        String name = args[0];

        if (name.equals("sites")) {
            sitesHelper.getSiteFeed(token);

        } else if (name.equals("newsite")) {
            if (args.length < 3) {
                System.out.flush();
                throw new SitesException("Wrong number of args");
            }

            SiteEntry siteEntry = null;

            if (args.length == 4) {
                siteEntry = sitesHelper.createSite(args[1], args[2], args[3]);
            } else if (args.length == 3) {
                siteEntry = sitesHelper.createSite(args[1], args[2]);
            }

            System.out.println("Created!");
            if (siteEntry.getHtmlLink() != null) {
                System.out.println("View it at " + siteEntry.getHtmlLink().getHref());
            }

        } else if (name.equals("copysite")) {
            if (args.length < 4) {
                System.out.flush();
                throw new SitesException("Wrong number of args");
            }

            SiteEntry siteEntry = siteEntry = sitesHelper.copySite(args[1], args[2], args[3]);

            System.out.println("Created!");
            if (siteEntry.getHtmlLink() != null) {
                System.out.println("View it at " + siteEntry.getHtmlLink().getHref());
            }

        } else if (name.equals("content")) {
            if (args.length == 1) {
                sitesHelper.listSiteContents("all");
            } else {
                sitesHelper.listSiteContents(args[1]);
            }

        } else if (name.equals("activity")) {
            sitesHelper.getActivityFeed();

        } else if (name.equals("create")) {
            if (args.length < 3) {
                listSupportedKinds();
                System.out.flush();
                throw new SitesException("Wrong number of args");
            }

            try {
                BaseContentEntry<?> newEntry;

                if (args.length == 4) {
                    newEntry = sitesHelper.createPage(args[1], args[2], args[3]);
                } else {
                    newEntry = sitesHelper.createPage(args[1], args[2]);
                }

                System.out.println("Created!");
                if (newEntry.getHtmlLink() != null) {
                    System.out.println("View it at " + newEntry.getHtmlLink().getHref());
                }
            } catch (InvalidEntryException e) {
                System.err.println(e.getResponseBody());
            }

        } else if (name.equals("delete")) {
            if (args.length == 1) {
                throw new SitesException("Wrong number of args");
            }

            sitesHelper.service.delete(new URL(sitesHelper.getContentFeedUrl() + args[1]), "*");
            System.out.println("Removed!");

        } else if (name.equals("upload")) {
            if (args.length < 3) {
                throw new SitesException("Wrong number of args");
            }

            try {
                AttachmentEntry newEntry = null;
                if (args.length == 4) {
                    newEntry = sitesHelper.uploadAttachment(
                            args[1], sitesHelper.getContentFeedUrl() + args[2], args[3]);
                } else {
                    newEntry = sitesHelper.uploadAttachment(
                            args[1], sitesHelper.getContentFeedUrl() + args[2], "");
                }

                if (newEntry.getHtmlLink() != null) {
                    System.out.println("View it at " + newEntry.getHtmlLink().getHref());
                }
            } catch (VersionConflictException e) {
                System.err.println(e.getResponseBody());
            }

        } else if (name.equals("download")) {
            if (args.length < 3) {
                throw new SitesException("Wrong number of args");
            }

            if (args[1].equals("all")) {
                sitesHelper.downloadAllAttachments(args[2]);
            } else {
                sitesHelper.downloadAttachment(args[1], args[2]);
            }

            System.out.println("Done!");

        } else if (name.equals("revisions")) {
            if (args.length == 1) {
                throw new SitesException("Wrong number of args");
            }

            sitesHelper.getRevisionFeed(args[1]);

        } else if (name.equals("acls")) {
            if (args.length == 1) {
                throw new SitesException("Wrong number of args");
            }

            sitesHelper.getAclFeed(args[1]);

        } else if (name.equals("kinds")) {
            listSupportedKinds();

        } else if (name.equals("help")) {
            printMessage(COMMAND_HELP_MESSAGE);

        } else if (name.startsWith("q") || name.startsWith("exit")) {
            return false;

        } else {
            System.out.println("Unknown command. Type 'help' for a list of commands.");
        }

        return true;
    }

    /**
     * Parses the command entered by the user into individual arguments.
     * 
     * @param command the entire command entered by the user to be broken up into arguments.
     */
    private String[] parseCommand(String command) {
        // Special commands need to be handled differently
        if (command.startsWith("create")) {
            // Break into args (create, kind, title, parentEntryID)
            return command.trim().split(" ", 4);
        } else if (command.startsWith("upload")) {
            // Break into args (upload, filepath, parentEntryID, description)
            return command.trim().split(" ", 4);
        }

        // Split into n args using a space as the separator.
        return command.trim().split(" ");
    }

    /**
     * Starts up the demo and prompts for commands.
     * 
     * @param username name of user to authenticate (e.g. user@gmail.com)
     * @param password password to use for authentication
     * @param feedUrl URL of the feed to connect to
     * @throws ServiceException
     * @throws IOException
     */
    public void run() throws IOException, ServiceException {
        printMessage(WELCOME_MESSAGE);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean run = true;
        while (run) {
            try {
                run = executeCommand(reader);
            } catch (SitesException e) {
                System.err.println(e.getMessage());
                printMessage(COMMAND_HELP_MESSAGE);
            }
        }
    }

    private static final String SCOPE = "https://sites.google.com/feeds/";
    private static final String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    // FILL THESE IN WITH YOUR VALUES FROM THE API CONSOLE
    private static final String CLIENT_ID = "623685080366.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "CP2nvtLuyM2g87P9KI0e9JgQ";

    /**
     * Runs the demo.
     * 
     * @param args the command-line arguments
     * 
     * @throws DocumentListException
     * @throws ServiceException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        SimpleCommandLineParser parser = new SimpleCommandLineParser(args);

        // Generate the URL to which we will direct users
        String authorizeUrl = new GoogleAuthorizationRequestUrl(CLIENT_ID,
                CALLBACK_URL, SCOPE).build();
        System.out.println("Paste this url in your browser: \n" + authorizeUrl);

        // Wait for the authorization code
        System.out.println("Type the code you received here: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String authorizationCode = in.readLine();

        // Exchange for an access and refresh token
        GoogleAuthorizationCodeGrant authRequest = new GoogleAuthorizationCodeGrant(TRANSPORT,
                JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, authorizationCode, CALLBACK_URL);
        authRequest.useBasicAuthorization = false;
        AccessTokenResponse authResponse = authRequest.execute();
        String accessToken = authResponse.accessToken;
        GoogleAccessProtectedResource access = new GoogleAccessProtectedResource(accessToken,
                TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, authResponse.refreshToken);
        HttpRequestFactory rf = TRANSPORT.createRequestFactory(access);
        System.out.println("Access token: " + authResponse.accessToken);

        // Make an authenticated request
//        GenericUrl shortenEndpoint =
//                new GenericUrl("https://www.googleapis.com/urlshortener/v1/url");
//        String requestBody =
//                "{\"longUrl\":\"http://farm6.static.flickr.com/5281/5686001474_e06f1587ff_o.jpg\"}";
//        HttpRequest request =
//                rf.buildPostRequest(shortenEndpoint, new ByteArrayContent(requestBody));
//        request.headers.contentType = "application/json";
//        
//        
//        
//        HttpResponse shortUrl = request.execute();
//        BufferedReader output = new BufferedReader(new InputStreamReader(shortUrl.getContent()));
//        System.out.println("Shorten Response: ");
//        for (String line = output.readLine(); line != null; line = output.readLine()) {
//            System.out.println(line);
//        }

        // Refresh a token (SHOULD ONLY BE DONE WHEN ACCESS TOKEN EXPIRES)
//        access.refreshToken();
        System.out.println("Original Token: " + accessToken + " New Token: "
                + access.getAccessToken());

        // String domain = parser.getValue("domain", "d");
        String domain = "google.com";

        // String siteName = parser.getValue("siteName", "site", "s");
        String siteName = "arjun-satyapal";

        boolean help = parser.containsKey("help", "h");
        boolean logItUp = parser.containsKey("log", "l");

        if (siteName == null || help) {
            printMessage(USAGE_MESSAGE);
            System.exit(1);
        }

        // If domain is set, use "site" for a non-Google Apps hosted Site.
        if (domain == null) {
            domain = "site";
        }

        String token = "Bearer " + accessToken;
        SitesDemo demo = null;
        if (token != null) {
            demo = new SitesDemo(APP_NAME, domain, siteName, token, logItUp);
        } else {
            printMessage(USAGE_MESSAGE);
            System.exit(1);
        }
        demo.run();
    }
}
