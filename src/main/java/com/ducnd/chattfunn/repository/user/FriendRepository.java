package com.ducnd.chattfunn.repository.user;

import com.ducnd.chattfunn.model.database.Friend;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM friend WHERE (friend.sender_id = :userId OR friend.receiver_id = :userId) AND friend.is_delete = 0 ")
    List<Friend> findAllByUserId(
            @Param(value = "userId") long userId
    );

    @Query(nativeQuery = true, value = "SELECT " +
            "(CASE WHEN friend.sender_id = :userId THEN friend.receiver_id ELSE friend.receiver_id END) as friend_id " +
            "FROM friend WHERE (friend.sender_id = :userId OR friend.receiver_id = :userId) AND friend.is_delete = 'false' ")
    List<Integer> findAllFriend(
            @Param(value = "userId") long userId
    );

    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM friend WHERE (friend.sender_id = :userId OR friend.receiver_id = :userId) AND (friend.sender_id IN :friendIds OR friend.receiver_id IN :friendIds) ")
    int countFriendsIn(
            @Param(value = "userId") int userId,
            @Param(value = "friendIds") List<Integer> friendIds
    );
}
