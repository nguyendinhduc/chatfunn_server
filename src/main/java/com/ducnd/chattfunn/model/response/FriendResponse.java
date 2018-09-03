package com.ducnd.chattfunn.model.response;

import com.ducnd.chattfunn.model.database.type.MessageType;
import org.joda.time.LocalDateTime;

public class FriendResponse {
    private long id;
    private long friendId;
    private Long senderId;
    private Long receiverId;
    private Long lastMessageId;
    private String displayNameFriend;
    private MessageType type;
    private String content;
    private String likeFile;
    private LocalDateTime created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
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

    public String getLikeFile() {
        return likeFile;
    }

    public void setLikeFile(String likeFile) {
        this.likeFile = likeFile;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getDisplayNameFriend() {
        return displayNameFriend;
    }

    public void setDisplayNameFriend(String displayNameFriend) {
        this.displayNameFriend = displayNameFriend;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }
}
