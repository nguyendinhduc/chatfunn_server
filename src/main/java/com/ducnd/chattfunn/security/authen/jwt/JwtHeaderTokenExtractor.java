package com.ducnd.chattfunn.security.authen.jwt;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.MessageResponses;
import com.ducnd.chattfunn.common.utils.StringUtils;
import com.ducnd.chattfunn.security.authen.extractor.TokenExtractor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {

    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
//            throw new AuthenticationServiceException("Authorization header cannot be blank!");
            throw new AuthenticationServiceException(MessageResponses.MESSAGE_MUST_LOGIN);
        }

        if (header.length() < Constants.HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return header.substring(Constants.HEADER_PREFIX.length(), header.length());
    }
}
