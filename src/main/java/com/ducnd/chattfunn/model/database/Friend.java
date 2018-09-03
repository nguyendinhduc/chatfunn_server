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
    private long id;
    private long senderId;
    private long receiverId;
    @CreatedDate
    @Generated(GenerationTime.INSERT)
    private LocalDateTime createdTime;
    private BooleanTypeDb isAccepted;
    private BooleanTypeDb isDelete;

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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(GenerationTime.INSERT)
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public BooleanTypeDb getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(BooleanTypeDb isAccepted) {
        this.isAccepted = isAccepted;
    }

    public BooleanTypeDb getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(BooleanTypeDb isDelete) {
        this.isDelete = isDelete;
    }
}
