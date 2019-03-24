package com.ducnd.chattfunn.common;

public interface MessageResponses {
    String MESSAGE_MUST_LOGIN = "You must login to access this function.";
    String USER_EXIST = "User exist!";
    String USER_NAME_OR_PASSWORD_INVALID = "Username or password invalid!";
    String FORMAT_VALUE_INVALID = "Format value invalid";
    String FRIEND_ID_NOT_NULL_OR_EMPTY = "FriendId not null or empty";
    String CAN_NOT_ADD_FRIEND_MY_ID = "Can not add my id to friend";

    String USER_NOT_FOUND = "FriendId not found";
    String FRIEND_ADD_FRIEND = "Friend added";
}
