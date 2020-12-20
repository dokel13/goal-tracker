package com.tracker.goal.repository;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.domain.GoalCategory;
import com.tracker.goal.domain.GoalStatus;
import com.tracker.goal.entity.GoalEntity;
import com.tracker.goal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.BitSet;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

    List<GoalEntity> findAllByUser_Id(Integer userId);
    List<Goal> findAllByUser(Integer userId);

    List<GoalEntity> findAllByStatusAndUser(GoalStatus status, UserEntity user);

    List<GoalEntity> findAllByCategoryAndUser(GoalCategory category, UserEntity user);

}
