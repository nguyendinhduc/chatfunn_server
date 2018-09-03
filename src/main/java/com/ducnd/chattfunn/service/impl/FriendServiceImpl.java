package com.ducnd.chattfunn.service.impl;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.manager.FriendManager;
import com.ducnd.chattfunn.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendManager manager;
    @Override
    public Object addFriend(List<Long> friendIds) throws ExceptionResponse {
        return manager.addFriend(friendIds);
    }

    @Override
    public Object getFriends() {
        return manager.getFriends();
    }
}
