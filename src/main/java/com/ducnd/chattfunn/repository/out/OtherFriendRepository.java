package com.ducnd.chattfunn.repository.out;

import com.ducnd.chattfunn.model.inter.OtherFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OtherFriendRepository extends JpaRepository<OtherFriend, Integer> {
    @Query(nativeQuery = true, value = "SELECT user_profile.id as id, " +
            "user_profile.display_name as display_name, " +
            "user_profile.is_online as is_online, " +
            "user_profile.avatar as avatar " +
            "FROM user_profile " +
            "WHERE user_profile.id NOT IN " +
            "(" +
            "SELECT (CASE WHEN friend.sender_id = :userId THEN friend.receiver_id ELSE friend.sender_id END) as friend_id " +
            "FROM " +
            "friend " +
            "where friend.sender_id <> :userId OR friend.receiver_id <> :userId " +
            ") AND user_profile.id <> :userId"
    )
    List<OtherFriend> findAllOtherFriend(@Param(value = "userId") int userId);
}
