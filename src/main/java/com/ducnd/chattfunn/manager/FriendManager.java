package com.ducnd.chattfunn.manager;

import com.ducnd.chattfunn.common.MessageResponses;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.common.utils.CommonUtils;
import com.ducnd.chattfunn.model.ObjectError;
import com.ducnd.chattfunn.model.database.Friend;
import com.ducnd.chattfunn.model.database.Message;
import com.ducnd.chattfunn.model.database.UserProfile;
import com.ducnd.chattfunn.model.database.type.BooleanTypeDb;
import com.ducnd.chattfunn.model.out.ContentCommon;
import com.ducnd.chattfunn.model.response.FriendResponse;
import com.ducnd.chattfunn.repository.out.ContentCommonRepository;
import com.ducnd.chattfunn.repository.out.OtherFriendRepository;
import com.ducnd.chattfunn.repository.user.FriendRepository;
import com.ducnd.chattfunn.repository.user.MessageRepository;
import com.ducnd.chattfunn.repository.user.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FriendManager implements MessageResponses {
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private ContentCommonRepository contentCommonRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private OtherFriendRepository otherFriendRepository;

    public Object addFriend(List<Integer> friendIds) throws ExceptionResponse {
        if (friendIds == null || friendIds.size() == 0) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, FRIEND_ID_NOT_NULL_OR_EMPTY),
                    HttpStatus.BAD_REQUEST);
        }
        int userId = CommonUtils.getUserLogin();
        if (friendIds.contains(userId)) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, CAN_NOT_ADD_FRIEND_MY_ID),
                    HttpStatus.BAD_REQUEST);
        }
        int countUser = userProfileRepository.countUser(friendIds);
        if (countUser != friendIds.size()) {
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, USER_NOT_FOUND),
                    HttpStatus.BAD_REQUEST);
        }
        int countFriendsExist = friendRepository.countFriendsIn(userId, friendIds);
        if (countFriendsExist > 0 ){
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, FRIEND_ADD_FRIEND),
                    HttpStatus.BAD_REQUEST);
        }
        List<Friend> friends = friendIds.stream().map(friendId -> {
            Friend friend = new Friend();
            friend.setSenderId(userId);
            friend.setReceiverId(friendId);
            return friend;
        }).collect(Collectors.toList());
        return friendRepository.save(friends);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Object getFriends() {
        int userId = CommonUtils.getUserLogin();
        List<Friend> friends = friendRepository.findAllByUserId(userId);
        List<Integer> friendIds = friends.stream().map(Friend::getReceiverId).collect(Collectors.toList());
        friendIds.addAll(
                friends.stream().map(Friend::getSenderId).collect(Collectors.toList())
        );
        CommonUtils.stream(friendIds);
        for (Integer friendId : friendIds) {
            if (friendId == userId) {
                friendIds.remove(friendId);
                break;
            }
        }
        if (friendIds.size() == 0) {
            return new ArrayList<>();
        }

        List<UserProfile> userProfiles = userProfileRepository.findAllByIds(friendIds);
//        List<ContentCommon> contentCommons = contentCommonRepository.findAllUserByUserIds(friendIds);
        List<Message> messages = messageRepository.findLastMessageAllByUserIds(friendIds, userId);

        List<FriendResponse> friendResponses = friends.stream().map(friend -> {
            FriendResponse friendResponse = new FriendResponse();
            friendResponse.setId(friend.getId());
            friendResponse.setFriendId(friend.getSenderId() == userId ? friend.getReceiverId() : friend.getSenderId());
            Message message = CommonUtils.findObject(messages, msg ->
                    (msg.getSenderId() == friend.getSenderId() && msg.getReceiverId() == friend.getReceiverId()) ||
                            (msg.getReceiverId() == friend.getSenderId() && msg.getSenderId() == friend.getReceiverId()));

            if (message != null) {
                friendResponse.setSenderId(message.getSenderId());
                friendResponse.setReceiverId(message.getReceiverId());
                friendResponse.setContent(message.getContent());
                friendResponse.setType(message.getType());
                friendResponse.setCreated(message.getCreatedTime());
            }
            UserProfile userProfile = CommonUtils.findObject(userProfiles, user -> user.getId() == friendResponse.getFriendId());
            if (userProfile != null) {
                friendResponse.setDisplayNameFriend(userProfile.getDisplayName());
                friendResponse.setFriendAvatar(userProfile.getAvatar());
                friendResponse.setOnline(userProfile.isOnline());
            }


            return friendResponse;
        }).collect(Collectors.toList());
        return friendResponses;
    }

    public Object findAllOtherFriend() {
        int userId = CommonUtils.getUserLogin();
        return otherFriendRepository.findAllOtherFriend(userId);
    }
}
