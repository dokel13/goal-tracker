package com.tracker.goal.service.impl;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.entity.GoalEntity;
import com.tracker.goal.repository.GoalRepository;
import com.tracker.goal.service.GoalService;
import com.tracker.goal.service.mapper.GoalMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;

    @Override
    public List<Goal> findAllByUserId(Integer userId) {
        try {
            System.out.println("UserId: " + userId);
            List<GoalEntity> goalsEntity = goalRepository.findAllByUser_Id(userId);
            System.out.println(goalsEntity);
            List<Goal> goals = goalsEntity.stream().map(GoalMapper::mapDomainFromEntity).collect(Collectors.toList());
            System.out.println(goals);
            return goals;
        } catch (Exception e) {
            return null;
        }
    }
}
