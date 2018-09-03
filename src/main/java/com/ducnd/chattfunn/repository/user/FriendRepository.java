package com.ducnd.chattfunn.repository.user;

import com.ducnd.chattfunn.model.database.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM friend WHERE (friend.sender_id = :userId OR friend.receiver_id = :userId) AND friend.is_delete = 'false' ")
    List<Friend> findAllByUserId(
            @Param(value = "userId") long userId
    );
}
