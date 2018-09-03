package com.ducnd.chattfunn.security.authen.jwt;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.security.authen.extractor.TokenExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ducnd on 6/25/17.
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenAuthenticationProcessingFilter.class);
    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    public JwtTokenAuthenticationProcessingFilter(RequestMatcher matcher,
                                                  AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = httpServletRequest.getHeader(Constants.HEADER_NAME_AUTH);
        LOG.info("attemptAuthentication tokenPayload: " + tokenPayload);
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(tokenExtractor.extract(tokenPayload)));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
