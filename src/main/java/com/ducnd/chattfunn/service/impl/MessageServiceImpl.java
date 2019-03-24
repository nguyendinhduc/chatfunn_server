package com.ducnd.chattfunn.service.impl;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.manager.MessageManager;
import com.ducnd.chattfunn.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageManager messageManager;
    @Override
    public Object getAllMessage(int friendId) throws ExceptionResponse {
        return messageManager.getAllMessage(friendId);
    }
}
