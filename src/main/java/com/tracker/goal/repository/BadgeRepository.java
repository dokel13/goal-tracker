package com.tracker.goal.repository;

import com.tracker.goal.entity.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<BadgeEntity, Integer> {

    @Query(value = "SELECT * FROM badges JOIN users_badges ON badge_id = badge WHERE user = :user", nativeQuery = true)
    List<BadgeEntity> findAllByUser(@Param("user") Integer user);
}
