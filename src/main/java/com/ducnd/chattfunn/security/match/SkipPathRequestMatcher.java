package com.ducnd.chattfunn.security.match;

import org.apache.http.client.methods.HttpOptions;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ducnd on 6/25/17.
 */
public class SkipPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matchers;
    private RequestMatcher processingMatcher;
    private List<String> pathsToSkip;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        List<RequestMatcher> m = pathsToSkip.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        matchers = new OrRequestMatcher(m);
        processingMatcher = new AntPathRequestMatcher(processingPath);
        this.pathsToSkip = pathsToSkip;
    }

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getMethod().equals(HttpOptions.METHOD_NAME)) {
            return false;
        }
//        return !matchers.matches(httpServletRequest) && processingMatcher.matches(httpServletRequest);
        return (!matchers.matches(httpServletRequest) )
                && processingMatcher.matches(httpServletRequest);
    }
}
