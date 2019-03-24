package com.ducnd.chattfunn.repository.out;

import com.ducnd.chattfunn.model.out.ContentCommon;
import com.ducnd.chattfunn.model.out.ContentCommonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentCommonRepository extends JpaRepository<ContentCommon, ContentCommonId> {
    @Query(nativeQuery = true, value = "SELECT user_profile.id AS id, user_profile.display_name AS name FROM user_profile WHERE user_profile.id IN :userIds")
    List<ContentCommon> findAllUserByUserIds(
            @Param(value = "userIds") List<Integer> userIds
    );
}
