package com.ducnd.chattfunn.manager;

import com.ducnd.chattfunn.common.MessageResponses;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.model.ObjectError;
import com.ducnd.chattfunn.model.database.UserProfile;
import com.ducnd.chattfunn.model.request.LoginRequest;
import com.ducnd.chattfunn.model.request.RegisterRequest;
import com.ducnd.chattfunn.repository.user.UserProfileRepository;
import com.ducnd.chattfunn.security.authen.jwt.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserManager implements MessageResponses {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public Object register(RegisterRequest request) throws ExceptionResponse {
        if (userProfileRepository.getCountByEmail(request.getEmail()) > 0) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, USER_EXIST),
                    HttpStatus.BAD_REQUEST);
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setAvatar(request.getAvatar());
        userProfile.setBirthDay(request.getBirthDay());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userProfile.setPassword(encoder.encode(request.getPassword()));
        userProfile.setDisplayName(request.getDisplayName());
        userProfile.setEmail(request.getEmail());
        userProfile.setToken(JwtAuthenticationToken.generateToken(userProfile));
        return userProfileRepository.save(userProfile);
    }

    public Object login(LoginRequest request) throws ExceptionResponse {
        UserProfile userProfile = userProfileRepository.findByEmail(request.getUsername());
        if (userProfile == null) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_NOT_FOUND, USER_EXIST),
                    HttpStatus.NOT_FOUND);

        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(request.getPassword(), userProfile.getPassword())) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, USER_NAME_OR_PASSWORD_INVALID),
                    HttpStatus.UNAUTHORIZED);
        }
        userProfile.setToken(JwtAuthenticationToken.generateToken(userProfile));
        return userProfile;
    }
}
