package com.ducnd.chattfunn.controller;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping(value = Constants.URL_MESSAGES)
    public ResponseEntity getAllMessage(
            @RequestParam(value = "frendId") int friendId
    ) throws ExceptionResponse {
        return new ResponseEntity<>(messageService.getAllMessage(friendId), HttpStatus.OK);
    }
}
