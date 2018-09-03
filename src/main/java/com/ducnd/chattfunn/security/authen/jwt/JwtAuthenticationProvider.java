package com.ducnd.chattfunn.security.authen.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ducnd on 6/25/17.
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
//        Condition condition = User.USER.USERNAME.eq(token.getCredentials().getUsername());
//        UserRecord userRecord = baseManager.getDslContext().selectFrom(User.USER).where(condition).fetchOne();
//        if (userRecord == null) {
//            throw new AuthenticationServiceException("fount fount startauction");
//        }
//        if ( !token.getCredentials().getToken().equals(userRecord.getToken())) {
//            throw new AuthenticationServiceException("token invalidate");
//        }
        List<GrantedAuthority> listRowAuthority = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(token.getCredentials(), null,
                listRowAuthority);
//        return new JwtAuthenticationToken(token.getCredentials(),
//                listRowAuthority);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication) ;
    }
}
