package com.tracker.goal.repository;

import com.tracker.goal.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

    List<GoalEntity> findAllByUser_Id(Integer userId);
}
