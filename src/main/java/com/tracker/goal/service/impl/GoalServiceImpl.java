package com.tracker.goal.service.impl;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.repository.GoalRepository;
import com.tracker.goal.service.GoalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;

    @Override
    public List<Goal> findAllByUserId(Integer userId) {
        try {
            List<Goal> goals = goalRepository.findAllByUser(userId);

            return goals;
        } catch (Exception e) {
            return null;
        }
    }
}
