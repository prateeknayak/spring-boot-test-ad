package com.example.springboot.ad.service;

import com.example.springboot.ad.exceptions.ClientNotAuthenticatedException;
import com.example.springboot.ad.exceptions.InvalidTokenException;
import com.example.springboot.ad.exceptions.TokenAlreadyExistsException;
import com.example.springboot.ad.model.security.AuthToken;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthTokenService {

    /**
     * Authentication token header being passed via API.
     */
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    /**
     * HashMap to store all the tokens. In ideal world tokens would be persisted in DB.
     */
    private static Map<String, AuthToken> tokens = new HashMap<>();

    /**
     * Get the token object from tokens map.
     *
     * @param tokenValue value of token being retrieved.
     * @return AuthToken object containing userDetails.
     */
    public AuthToken getToken(String tokenValue) throws ClientNotAuthenticatedException {
        AuthToken token = tokens.get(tokenValue);
        if (null == token) {
            throw new ClientNotAuthenticatedException();
        }
        return token;
    }

    /**
     * Check if the provided token in the API call is valid.
     * As of now, it only checks if the AuthToken exists and is not expired.
     *
     * @param tokenValue value of the token being retrieved.
     * @return boolean indicating if the token is valid or not.
     * @throws Exception
     */
    public boolean isTokenValid(String tokenValue) throws InvalidTokenException, ClientNotAuthenticatedException {
        AuthToken token = getToken(tokenValue);
        if (null == token) {
            throw new InvalidTokenException();
        }
        LocalDateTime timeFourHoursAgoFromNow = LocalDateTime.now().minusHours(4);
        return token.getTimeCreated().isAfter(timeFourHoursAgoFromNow);
    }

    /**
     * Generates token value for the new AuthToken to be stored.
     * @param username
     * @return
     */
    private String generateToken(String username) {
        return  UUID.randomUUID() + username;
    }

    /**
     * Adds token to the tokens map. This is where we persist token using token repo.
     * @param authToken Actual token object being persisted
     * @throws Exception
     */
    public void storeToken(AuthToken authToken) throws TokenAlreadyExistsException {
        if (null != tokens.get(authToken.getTokenValue())) {
            throw new TokenAlreadyExistsException();
        }
        tokens.put(authToken.getTokenValue(), authToken);
    }

    public void removeToken(String tokenValue) throws InvalidTokenException {
        if (null == tokens.get(tokenValue)) {
            throw new InvalidTokenException();
        }
        tokens.remove(tokenValue);
    }
}
