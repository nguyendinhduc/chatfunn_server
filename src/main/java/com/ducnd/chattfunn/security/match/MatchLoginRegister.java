package com.ducnd.chattfunn.security.match;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ducnd on 6/25/17.
 */
public class MatchLoginRegister implements RequestMatcher {
    private OrRequestMatcher orRequestMatcher;

    public MatchLoginRegister(List<String> matchs) {
        List<RequestMatcher> antPathMatchers = matchs.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        orRequestMatcher = new OrRequestMatcher(antPathMatchers);
    }

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        return orRequestMatcher.matches(httpServletRequest);
    }
}
