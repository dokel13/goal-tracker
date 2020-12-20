package com.tracker.goal.service;

import com.tracker.goal.domain.Goal;

import java.util.List;

public interface GoalService {

    List<Goal> findAllByUserId(Integer userId);
}
