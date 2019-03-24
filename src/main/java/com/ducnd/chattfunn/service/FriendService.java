package com.ducnd.chattfunn.service;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;

import java.util.List;

public interface FriendService {

    Object addFriend(List<Integer> friendIds) throws ExceptionResponse;

    Object getFriends();

    Object findAllOtherFriend() throws ExceptionResponse;
}
