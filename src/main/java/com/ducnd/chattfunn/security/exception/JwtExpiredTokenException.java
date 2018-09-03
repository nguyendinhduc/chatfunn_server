package com.ducnd.chattfunn.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtExpiredTokenException extends AuthenticationException {
    private String token;
    public JwtExpiredTokenException(String msg, String token, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
