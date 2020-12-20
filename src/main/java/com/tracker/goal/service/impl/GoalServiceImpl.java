package com.tracker.goal.service.impl;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.ServiceRuntimeException;
import com.tracker.goal.repository.GoalRepository;
import com.tracker.goal.repository.UserRepository;
import com.tracker.goal.service.GoalService;
import com.tracker.goal.service.mapper.GoalMapper;
import com.tracker.goal.service.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Override
    public List<Goal> findAllByUserId(Integer userId) {
        return goalRepository.findAllByUser_Id(userId).stream()
                .map(goalMapper::mapDomainFromEntity).collect(toList());
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

    @Override
    public Goal save(Goal goal) {
        return goalMapper.mapDomainFromEntity(goalRepository.save(goalMapper
                .mapEntityFromDomain(goal, userMapper.mapEntityFromDomain(goal.getUser()))));
    }

    @Override
    public void delete(Integer goalId, Integer userId) {
        boolean anyMatch = findAllByUserId(userId).stream().map(Goal::getId).anyMatch(id -> id.equals(goalId));
        if (anyMatch) {
            goalRepository.deleteById(goalId);
        } else {
            throw new ServiceRuntimeException("Cannot delete goal!!!");
        }
    }

    private UserEntity getUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
