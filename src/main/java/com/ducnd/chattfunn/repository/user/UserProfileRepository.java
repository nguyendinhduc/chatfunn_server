package com.ducnd.chattfunn.repository.user;

import com.ducnd.chattfunn.model.database.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM user_profile WHERE user_profile.email = :email")
    int getCountByEmail(
            @Param(value = "email") String email
    );

    @Query(nativeQuery = true, value = "SELECT * FROM user_profile WHERE user_profile.email = :username")
    UserProfile findByEmail(
            @Param(value = "username") String username
    );

    @Query(nativeQuery = true, value = "SELECT * FROM user_profile WHERE user_profile.id IN :userIds")
    List<UserProfile> findAllByIds(
            @Param(value = "userIds") List<Long> userIds
    );

    @Query(nativeQuery = true, value = "SELECT COUNT(user_profile.id) FROM user_profile WHERE id IN :friendIds")
    int countUser(
            @Param(value = "friendIds") List<Long> friendIds
    );
}
