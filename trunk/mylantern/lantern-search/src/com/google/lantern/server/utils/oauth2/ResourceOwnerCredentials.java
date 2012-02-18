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
package com.google.lantern.server.utils.oauth2;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.lantern.server.exceptions.credential.CredentialCouldNotBeRefreshed;

import java.util.logging.Logger;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.appengine.api.users.User;
import com.google.lantern.server.persistence.dao.UserDao;
import com.google.lantern.server.persistence.entities.UserEntity;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.server.utils.sites.SitesUrl;
import com.google.lantern.testingutils.SitesConfigHelper;
import java.io.IOException;

/**
 * 
 * @author Arjun Satyapal
 */
public class ResourceOwnerCredentials {
    private static Logger logger = Logger.getLogger(ResourceOwnerCredentials.class.getName());

    /**
     * This will load userEntity from DataStore.
     * 
     * @return {@link UserEntity}
     * 
     * @throws UserNotLoggedInException
     * @throws IOException
     */
    public static UserEntity loadUserEntity() throws UserNotLoggedInException {
        // TODO(arjuns) : Later replace this with Guice Modules.
        if (AppEngineUtils.isJunitTesting()) {
            return getUserCredentialsFromFileSystem();
        } else {
            return UserDao.getByUserId(AppEngineUtils.getUserId());
        }
    }

    private static UserEntity getUserCredentialsFromFileSystem() throws UserNotLoggedInException {
        User user = AppEngineUtils.getUser();
        String authCallBackUrl;
        try {
            authCallBackUrl = ResourceConsumerCredentials.getAuthCallbackUri();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

        String accessToken = SitesConfigHelper.getAccessToken();
        String refreshToken = SitesConfigHelper.getRefreshToken();
        long expiresIn = SitesConfigHelper.getExiresIn();

        return new UserEntity(user, AccessTokenType.BEARER, accessToken, refreshToken, expiresIn,
                SitesUrl.getRootUrl(), authCallBackUrl);
    }

    /**
     * This will load UserEntity from DataStore. And in case Authorization has Expired, then
     * accessToken will be refreshed, stored in DB, and then updated UserEntity will be returned.
     * 
     * @return
     * @throws UserNotLoggedInException
     * @throws IOException
     * @throws CredentialCouldNotBeRefreshed
     */
    public static UserEntity loadValidUserEntity() throws UserNotLoggedInException, IOException,
            CredentialCouldNotBeRefreshed {
        UserEntity userEntity = loadUserEntity();

        if (userEntity.isValid()) {
            return userEntity;
        }

        return refreshUserEntity(userEntity);
    }

    /**
     * This method will refresh the AccessToken for the Logged-in user.
     * 
     * @param userEntity
     * @return UserEntity with valid accessToken.
     * @throws IOException
     * @throws UserNotLoggedInException
     * @throws CredentialCouldNotBeRefreshed
     */
    protected static UserEntity refreshUserEntity(UserEntity userEntity) throws IOException,
            UserNotLoggedInException, CredentialCouldNotBeRefreshed {
        // TODO(arjuns) : Temporary hack to run unittests.
        if (AppEngineUtils.isJunitTesting()) {
            refreshResourceOwnerCredentialsOnFilesSystem();
            return getUserCredentialsFromFileSystem();
        }

        // Generate a refresh token.
        GoogleAccessProtectedResource newAccess = new GoogleAccessProtectedResource(
                userEntity.getAccessToken(),
                AppEngineUtils.getHttpTransport(),
                AppEngineUtils.getJsonFactory(),
                ResourceConsumerCredentials.getClientId(),
                ResourceConsumerCredentials.getClientSecret(),
                userEntity.getRefreshToken());
        boolean isRefreshDone = newAccess.refreshToken();

        if (isRefreshDone == false) {
            logger.severe("Failed to refresh token for user : " + userEntity);
        }

        userEntity.setAccessToken(newAccess.getAccessToken());
        // TODO(arjuns) : Setting some artificial value.
        userEntity.setAccessTokenExpiryTimeInMillis(60 * 1000L);
        UserDao.updateUser(userEntity);
        return userEntity;
    }

    private static void refreshResourceOwnerCredentialsOnFilesSystem() {
        SitesConfigHelper.refreshAccessTokenForResourceOwner();
    }
}
