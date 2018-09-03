package com.ducnd.chattfunn.service.impl;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.manager.UserManager;
import com.ducnd.chattfunn.model.request.LoginRequest;
import com.ducnd.chattfunn.model.request.RegisterRequest;
import com.ducnd.chattfunn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserManager userManager;
    @Override
    public Object register(RegisterRequest request) throws ExceptionResponse {
        return userManager.register(request);
    }

    @Override
    public Object login(LoginRequest request) throws ExceptionResponse {
        return userManager.login(request);
    }
}
