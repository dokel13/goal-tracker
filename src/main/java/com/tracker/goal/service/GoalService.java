package com.tracker.goal.service;

import com.tracker.goal.domain.Goal;

import java.util.List;

public interface GoalService {

    List<Goal> findAllByUserId(Integer userId);

    List<Goal> findByStatusAndUser(String status, Integer id);

    List<Goal> findByCategoryAndUser(String category, Integer id);

    Goal save(Goal goal);
}
