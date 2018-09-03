package com.ducnd.chattfunn.controller;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {
    @Autowired
    private FriendService service;

    @PostMapping(value = Constants.URL_FRIEND)
    public ResponseEntity addFriend(
            @RequestParam(value = "friendIds") List<Long> friendIds
    ) throws ExceptionResponse {
        return new ResponseEntity(service.addFriend(friendIds), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_FRIENDS)
    public ResponseEntity getFriends(
    ) throws ExceptionResponse {
        return new ResponseEntity(service.getFriends(), HttpStatus.OK);
    }
}
