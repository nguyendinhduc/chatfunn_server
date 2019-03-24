package com.ducnd.chattfunn.security.match;

import org.apache.http.client.methods.HttpOptions;
import org.springframework.http.HttpMethod;
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

    public SkipPathRequestMatcher(List<String> listNotmapPost, List<String> listNotmapGet, String processingPath) {
        List<RequestMatcher> m = listNotmapPost.stream().map(o-> new AntPathRequestMatcher(o, HttpMethod.POST.name())).collect(Collectors.toList());
        m.addAll(listNotmapGet.stream().map(o-> new AntPathRequestMatcher(o, HttpMethod.GET.name())).collect(Collectors.toList()));

        matchers = new OrRequestMatcher(m);
        processingMatcher = new AntPathRequestMatcher(processingPath);
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
