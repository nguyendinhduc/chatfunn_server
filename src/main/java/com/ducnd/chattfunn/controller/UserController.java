package com.ducnd.chattfunn.controller;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.model.request.LoginRequest;
import com.ducnd.chattfunn.model.request.RegisterRequest;
import com.ducnd.chattfunn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value = Constants.URL_REGISTER)
    public ResponseEntity register(
            @RequestBody @Valid RegisterRequest request
    ) throws ExceptionResponse {
        return new ResponseEntity(service.register(request), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_LOGIN)
    public ResponseEntity login(
            @RequestBody @Valid LoginRequest request
    ) throws ExceptionResponse{
        return new ResponseEntity(service.login(request), HttpStatus.OK);
    }
}
