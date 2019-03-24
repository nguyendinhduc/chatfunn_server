package com.ducnd.chattfunn.model.database;

import com.ducnd.chattfunn.model.database.type.BooleanTypeDb;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int senderId;
    private int receiverId;
    @CreatedDate
    @Generated(GenerationTime.INSERT)
    private LocalDateTime createdTime;
    private boolean isDelete;

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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

}
