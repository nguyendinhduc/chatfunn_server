package com.ducnd.chattfunn.security.authen.jwt;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.model.database.UserProfile;
import com.ducnd.chattfunn.security.exception.JwtExpiredTokenException;
import com.ducnd.chattfunn.security.user.UserContext;
import io.jsonwebtoken.*;
import org.joda.time.LocalDateTime;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;


/**
 * Created by ducnd on 6/25/17.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private UserContext userContext;

    public JwtAuthenticationToken(String token) {
        super(null);
        Jws<Claims> claimsJws = parseClaims(token);
        Claims body = claimsJws.getBody();
        String email = body.get("email", String.class);
        int id = Integer.valueOf(body.getId());
        String displayName = body.get("display_name", String.class);

        this.userContext = new UserContext(id, email, token);
        userContext.setId(id);
        userContext.setDisplayName(displayName);
        userContext.setEmail(email);
        this.setAuthenticated(false);
    }


    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userContext = userContext;
    }

    @Override
    public UserContext getCredentials() {
        return userContext;
    }

    private static Jws<Claims> parseClaims(String token) {
        System.out.println("token: " + token);
        try {
            return Jwts.parser().setSigningKey(
                    new String(Base64.getEncoder().encode(Constants.KEY_TOKEN.getBytes()))).parseClaimsJws(
                    token
            );
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {

            ex.printStackTrace();
            throw new BadCredentialsException("Invalid JWT token", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(expiredEx.getMessage(), token, expiredEx);
        }
    }

    public static String generateToken(UserProfile userProfile) {
        return Jwts.builder().setId(userProfile.getId() + "")
                .setIssuedAt(LocalDateTime.now().toDate())
                .setSubject(userProfile.getEmail())
                .claim("email", userProfile.getEmail())
                .claim("display_name", userProfile.getEmail())
                .setIssuer("no comment")
                .setExpiration(new Date(LocalDateTime.now().toDate().getTime() + 1008 * 60L * 1000))
                .signWith(SignatureAlgorithm.HS256, Constants.KEY_TOKEN.getBytes())
                .compact();
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public boolean isEmpty() {
        return userContext == null;
    }

}
