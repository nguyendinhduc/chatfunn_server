package com.ducnd.chattfunn.model.database;

import com.ducnd.chattfunn.model.database.type.BooleanTypeDb;
import com.ducnd.chattfunn.model.database.type.MessageType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private MessageType type;
    @CreatedDate
    @Generated(GenerationTime.INSERT)
    private LocalDateTime createdTime;
    @LastModifiedDate
    @Generated(GenerationTime.ALWAYS)
    private LocalDateTime lastUpdate;
    private boolean isRead;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
