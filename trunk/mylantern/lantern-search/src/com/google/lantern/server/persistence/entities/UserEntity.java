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
package com.google.lantern.server.persistence.entities;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.appengine.api.users.User;
import com.google.common.base.Strings;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.server.utils.oauth2.AccessTokenType;
import java.io.Serializable;
import javax.persistence.Id;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class UserEntity implements Serializable {
    @Id
    Long id;

    String email;
    String userId;

    // TODO(arjuns) : Move these access related things in another class.
    AccessTokenType accessTokenType;
    String accessToken;
    String refreshToken;
    // Url where user's browser was redirected.
    // TODO(arjuns) : Change to URL.
    String authorizationCbUrl;
    String scope;
    long accessTokenExpiryTimeInMillis;

    // For Objectify.
    protected UserEntity() {
    }

    @Override
    public String toString() {
        StringBuilder builder =
                new StringBuilder("id[").append(id)
                        .append("], email[").append(email)
                        .append("], userId[").append(userId)
                        .append("], accessTokenType[").append(accessTokenType)
                        .append("], authCbUrl[").append(authorizationCbUrl)
                        .append("], scope[").append(scope)
                        .append("], expiryInMillis[").append(accessTokenExpiryTimeInMillis)
                        .append("].");

        return builder.toString();
    }

    /**
     * Forcing to pass scope, because many-a-times, scope is not returned as part of the
     * AccessTokenResponse and AccessTokens are sensitive to scopes.
     * 
     * @param user
     * @param accessTokenResponse
     * @param scope
     * @param authorizationCbUrl
     */
    public UserEntity(User user, AccessTokenType type, String accessToken, String refreshToken,
            long expiresInSecsFromNow, String scope,
            String authorizationCbUrl) {
        checkNotNull(user, "user cannot be null.");
        this.email = user.getEmail();
        this.userId = user.getUserId();

        this.accessTokenType = checkNotNull(type);

        checkArgument(!Strings.isNullOrEmpty(accessToken));
        this.accessToken = accessToken;

        checkArgument(!Strings.isNullOrEmpty(refreshToken));
        this.refreshToken = refreshToken;

        // TODO(arjuns) : Fix this.
        this.accessTokenExpiryTimeInMillis = getAccessTokenExpiryTimeInMillis(expiresInSecsFromNow);

        checkArgument(!Strings.isNullOrEmpty(scope),
                "scope cannot be null/empty.");
        this.scope = scope;

        checkArgument(!Strings.isNullOrEmpty(authorizationCbUrl),
                "authorizationCbUrl cannot be null/empty.");
        this.authorizationCbUrl = authorizationCbUrl;
    }

    private long getAccessTokenExpiryTimeInMillis(long expiresInSecsFromNow) {
        // Since AppEngine timeOut is 30 seconds, so credentials that are not valid for 30 seconds
        // will be treated as expired and then token will be refreshed.

        long timeToExpireInMillis = (expiresInSecsFromNow - 30) * 1000;
        long currentTimeInMillis = AppEngineUtils.getCurrenTimeInMillis();

        return currentTimeInMillis + timeToExpireInMillis;
    }

    protected AccessTokenType getAccessTokenType(AccessTokenResponse accessTokenResponse) {
        String typeStr = (String) accessTokenResponse.getUnknownKeys().get("token_type");
        return AccessTokenType.getAccessTokenTypeByString(typeStr);
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public AccessTokenType getAccessTokenType() {
        return accessTokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAuthorizationCbUrl() {
        return authorizationCbUrl;
    }

    public long getAccessTokenExpiryTimeInMillis() {
        return accessTokenExpiryTimeInMillis;
    }

    public void setAccessTokenExpiryTimeInMillis(Long expiryTimeInMillis) {
        this.accessTokenExpiryTimeInMillis = expiryTimeInMillis;
    }

    public boolean isValid() {
        return AppEngineUtils.getCurrenTimeInMillis() < accessTokenExpiryTimeInMillis;
    }

    public String getAuthorizationHeader() {
        return accessTokenType.get() + " " + accessToken;
    }
}
