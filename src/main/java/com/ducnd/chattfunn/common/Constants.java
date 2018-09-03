package com.ducnd.chattfunn.common;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    String KEY_TOKEN = "chatfunn#2018";
    String END_POINT_MATCH_API = "/api/**";
    String HEADER_PREFIX = "Bearer ";
    String HEADER_NAME_AUTH = "Authorization";
    String API = "/api";
    String USER = "/user";
    String LOGIN = "/login";
    String REGISTER = "/register";
    String MESSAGE = "/message";
    String FRIEND = "/friend";
    String FRIENDS = "/friends";

    String URL_LOGIN = API + USER + LOGIN;
    String URL_REGISTER = API + USER + REGISTER;
    String URL_MESSAGE = API + USER + MESSAGE;
    String URL_FRIEND = API + USER + FRIEND;
    String URL_FRIENDS = API + USER + FRIENDS;

    String FORMAT_DATE = "yyyy-MM-dd";
    String FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    int PAGE_SIZE_DEFAULT = 20;

    List<String> API_SKIP_AUTHEN = Arrays.asList(
            URL_LOGIN,
            URL_REGISTER
    );

}
