package com.tracker.goal.service.impl;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.entity.GoalEntity;
import com.tracker.goal.repository.GoalRepository;
import com.tracker.goal.repository.UserRepository;
import com.tracker.goal.service.GoalService;
import com.tracker.goal.service.mapper.GoalMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;
    private UserRepository userRepository;
    private GoalMapper goalMapper;

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

    @Override
    public List<Goal> findByStatusAndUser(String status, Integer userId) {
        return goalRepository.findAllByStatusAndUser(status, getUser(userId))
                .stream().map(goalMapper::mapDomainFromEntity).collect(toList());
    }

    @Override
    public List<Goal> findByCategoryAndUser(String category, Integer userId) {
        return goalRepository.findAllByCategoryAndUser(category, getUser(userId))
                .stream().map(goalMapper::mapDomainFromEntity).collect(toList());
    }

    private UserEntity getUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
