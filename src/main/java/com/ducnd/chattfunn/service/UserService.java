package com.ducnd.chattfunn.service;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.model.request.LoginRequest;
import com.ducnd.chattfunn.model.request.RegisterRequest;

public interface UserService {

    Object register(RegisterRequest request) throws ExceptionResponse;

    Object login(LoginRequest request) throws ExceptionResponse;
}
