package com.ducnd.chattfunn.model.database;

import com.ducnd.chattfunn.model.database.type.BooleanTypeDb;
import com.ducnd.chattfunn.model.database.type.MessageType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long senderId;
    private long receiverId;
    private String content;
    private String linkFile;
    @CreatedDate
    @Generated(GenerationTime.INSERT)
    private LocalDateTime createdTime;
    private BooleanTypeDb isRead;
    private MessageType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinkFile() {
        return linkFile;
    }

    public void setLinkFile(String linkFile) {
        this.linkFile = linkFile;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(GenerationTime.INSERT)
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public BooleanTypeDb getIsRead() {
        return isRead;
    }

    public void setIsRead(BooleanTypeDb isRead) {
        this.isRead = isRead;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
