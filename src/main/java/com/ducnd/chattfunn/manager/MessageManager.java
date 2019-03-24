package com.ducnd.chattfunn.manager;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.common.utils.CommonUtils;
import com.ducnd.chattfunn.model.ObjectError;
import com.ducnd.chattfunn.model.database.Message;
import com.ducnd.chattfunn.model.database.UserProfile;
import com.ducnd.chattfunn.repository.user.MessageRepository;
import com.ducnd.chattfunn.repository.user.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageManager {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;


    public Object getAllMessage(int friendId) throws ExceptionResponse {
        UserProfile userProfile = userProfileRepository.findOne(friendId);
        if (userProfile == null) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, "Friend not found"),
                    HttpStatus.BAD_REQUEST);
        }

        int userId = CommonUtils.getUserLogin();
        List<Message> messages = messageRepository.findAllMessage(friendId, userId);
        return messages;
    }
}
