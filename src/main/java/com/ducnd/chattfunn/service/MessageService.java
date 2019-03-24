package com.ducnd.chattfunn.service;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;

public interface MessageService {
    Object getAllMessage(int friendId) throws ExceptionResponse;
}
