package com.ducnd.chattfunn.model.response;

import com.ducnd.chattfunn.model.database.type.MessageType;
import org.joda.time.LocalDateTime;

public class FriendResponse {
    private int id;
    private int friendId;
    private Integer senderId;
    private Integer receiverId;
    private String displayNameFriend;
    private MessageType type;
    private String content;
    private String friendAvatar;
    private LocalDateTime created;
    private boolean isOnline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }


    public String getDisplayNameFriend() {
        return displayNameFriend;
    }

    public void setDisplayNameFriend(String displayNameFriend) {
        this.displayNameFriend = displayNameFriend;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getFriendAvatar() {
        return friendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        this.friendAvatar = friendAvatar;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
