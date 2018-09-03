package com.ducnd.chattfunn.security;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.security.authen.ajax.AjaxTokenAuthenticationFailureHandler;
import com.ducnd.chattfunn.security.authen.extractor.TokenExtractor;
import com.ducnd.chattfunn.security.authen.jwt.JwtAuthenticationProvider;
import com.ducnd.chattfunn.security.authen.jwt.JwtTokenAuthenticationProcessingFilter;
import com.ducnd.chattfunn.security.match.SkipPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

/**
 * Created by ducnd on 6/25/17.
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenExtractor tokenExtractor;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(ajaxLoginProcessingFilter);
        auth.authenticationProvider(jwtAuthenticationProvider);
//        auth.authenticationProvider(registerAuthenticationProvider);
    }

//    public AjaxLoginRegisterProcessingFilter getFilter() {
//        List<String> matchs = new ArrayList<>();
//        matchs.add(Constants.ENPOINT_LOGIN);
//        matchs.add(Constants.ENPOINT_REGISTER);
//        MatchLoginRegister matchLoginRegister = new MatchLoginRegister(matchs);
//        AjaxLoginRegisterProcessingFilter filter =
//                new AjaxLoginRegisterProcessingFilter(matchLoginRegister, authenticationSuccessHandler, new AjaxAuthenticationLoginRegisterFailureHandler(objectMapper), objectMapper);
//        try {
//            filter.setAuthenticationManager(authenticationManagerBean());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return filter;
//    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(List<String> listNotmap) {


        RequestMatcher matcher = new SkipPathRequestMatcher(listNotmap, Constants.END_POINT_MATCH_API);
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(matcher,
                new AjaxTokenAuthenticationFailureHandler(objectMapper),
                tokenExtractor);
        try {
            filter.setAuthenticationManager(authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(Constants.API_SKIP_AUTHEN.toArray(new String[0]))
                .permitAll()
                .and().authorizeRequests()
                .antMatchers(Constants.END_POINT_MATCH_API).authenticated()
                .and()
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(Constants.API_SKIP_AUTHEN),
                        UsernamePasswordAuthenticationFilter.class);


    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
