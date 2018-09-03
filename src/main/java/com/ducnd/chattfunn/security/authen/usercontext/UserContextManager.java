package com.ducnd.chattfunn.security.authen.usercontext;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.common.utils.StringUtils;
import com.ducnd.chattfunn.model.ObjectError;
import com.ducnd.chattfunn.security.authen.extractor.TokenExtractor;
import com.ducnd.chattfunn.security.authen.jwt.JwtAuthenticationToken;
import com.ducnd.chattfunn.security.exception.JwtExpiredTokenException;
import com.ducnd.chattfunn.security.user.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserContextManager {
    private static final Logger LOG = LoggerFactory.getLogger(UserContextManager.class);
    @Autowired
    private TokenExtractor tokenExtractor;

    public UserContext getUserContext(HttpServletRequest httpServletRequest) throws ExceptionResponse {
        String tokenPayload = httpServletRequest.getHeader(Constants.HEADER_NAME_AUTH);
        LOG.info("attemptAuthentication tokenPayload: " + tokenPayload);
        if (!StringUtils.isBlank(tokenPayload) && tokenPayload.contains(Constants.HEADER_PREFIX)) {
            try {
                String token = tokenExtractor.extract(tokenPayload);
                if (!StringUtils.isBlank(token)) {
                    return new JwtAuthenticationToken(token).getCredentials();
                }
            } catch (AuthenticationServiceException ex) {
                ex.printStackTrace();
                throw new ExceptionResponse(
                        new ObjectError(ObjectError.ERROR_PARAM, ex.getMessage()),
                        HttpStatus.UNAUTHORIZED
                );
            } catch (BadCredentialsException | JwtExpiredTokenException e) {
                throw new ExceptionResponse(
                        new ObjectError(ObjectError.ERROR_PARAM, e.getMessage()),
                        HttpStatus.UNAUTHORIZED
                );
            }
        }

        return null;
    }
}
