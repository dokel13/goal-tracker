package com.tracker.goal.repository;

import com.tracker.goal.domain.GoalCategory;
import com.tracker.goal.domain.GoalStatus;
import com.tracker.goal.entity.GoalEntity;
import com.tracker.goal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

    List<GoalEntity> findAllByUser_Id(Integer userId);

    List<GoalEntity> findAllByStatusAndUser(GoalStatus status, UserEntity user);

    List<GoalEntity> findAllByCategoryAndUser(GoalCategory category, UserEntity user);

    @Modifying
    @Query(value = "UPDATE GoalEntity g SET g.title = ?2, g.category = ?3, g.estimate = ?4, " +
            "g.daysPassed = ?5, g.status = ?6 WHERE g.id = ?1 AND g.user = ?7")
    void update(Integer goalId, String title,
                GoalCategory category, Integer estimate,
                Integer daysPassed, GoalStatus status,
                UserEntity user);
}
