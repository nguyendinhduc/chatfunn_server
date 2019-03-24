package com.ducnd.chattfunn.repository.user;

import com.ducnd.chattfunn.model.database.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM message WHERE message.id IN " +
            "(SELECT message.id AS id FROM message JOIN " +
            "(SELECT msg_friend.friend_id AS friend_id, MAX(msg_friend.created_time) AS max_time FROM " +
            "(SELECT (CASE WHEN message.sender_id = :userId THEN message.receiver_id ELSE message.sender_id END) AS friend_id, " +
            "message.id, message.created_time " +
            "FROM message WHERE (message.sender_id IN :friendIds OR message.receiver_id IN :friendIds) AND message.is_delete = 0) AS msg_friend " +
            "GROUP BY msg_friend.friend_id) AS fr " +
            "ON (message.sender_id = fr.friend_id OR message.receiver_id = fr.friend_id) AND message.created_time = fr.max_time) " +
            "AND message.is_delete = 0"
    )
    List<Message> findLastMessageAllByUserIds(
            @Param(value = "friendIds") List<Integer> friendIds,
            @Param(value = "userId") int userId
    );

    @Query(nativeQuery = true, value = "SELECT * FROM " +
            "message " +
            "WHERE " +
            "((message.sender_id = :userId AND message.receiver_id = :friendId) OR " +
            "(message.receiver_id = :userId AND message.sender_id = :friendId)) AND " +
            "message.is_delete = 0 " +
            "ORDER BY message.created_time DESC " +
            "LIMIT 200"
    )
    List<Message> findAllMessage(
            @Param(value = "friendId") int friendId,
            @Param(value = "userId") int userId
    );
}
