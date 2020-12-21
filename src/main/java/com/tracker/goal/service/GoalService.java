package com.tracker.goal.service;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.domain.User;

import java.util.List;

public interface GoalService {

    List<Goal> findAllByUserId(Integer userId);

    List<Goal> findByStatusAndUser(String status, User user);

    List<Goal> findByCategoryAndUser(String category, User user);

    void save(Goal goal);

    void delete(Integer goalId, Integer userId);

    void update(Goal goal);

    Goal findById(Integer id);
}
