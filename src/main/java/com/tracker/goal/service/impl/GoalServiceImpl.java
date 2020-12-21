package com.tracker.goal.service.impl;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.domain.GoalCategory;
import com.tracker.goal.domain.GoalStatus;
import com.tracker.goal.domain.User;
import com.tracker.goal.exception.ServiceRuntimeException;
import com.tracker.goal.repository.GoalRepository;
import com.tracker.goal.service.GoalService;
import com.tracker.goal.service.mapper.GoalMapper;
import com.tracker.goal.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;
    private GoalMapper goalMapper;
    private UserMapper userMapper;

    @Override
    public List<Goal> findAllByUserId(Integer userId) {
        return goalRepository.findAllByUser_Id(userId).stream()
                .map(goalMapper::mapDomainFromEntity).collect(toList());
    }

    @Override
    public List<Goal> findByStatusAndUser(String status, User user) {
        return goalRepository.findAllByStatusAndUser(GoalStatus
                .valueOf(status.toUpperCase()), userMapper.mapEntityFromDomain(user))
                .stream().map(goalMapper::mapDomainFromEntity).collect(toList());
    }

    @Override
    public List<Goal> findByCategoryAndUser(String category, User user) {
        return goalRepository.findAllByCategoryAndUser(GoalCategory
                .valueOf(category.toUpperCase()), userMapper.mapEntityFromDomain(user))
                .stream().map(goalMapper::mapDomainFromEntity).collect(toList());
    }

    @Override
    public void save(Goal goal) {
        goalRepository.save(goalMapper
                .mapEntityFromDomain(goal, userMapper.mapEntityFromDomain(goal.getUser())));
    }

    @Transactional
    @Override
    public void delete(Integer goalId, Integer userId) {
        boolean anyMatch = findAllByUserId(userId).stream().map(Goal::getId).anyMatch(id -> id.equals(goalId));
        if (anyMatch) {
            goalRepository.deleteById(goalId);
        } else {
            String message = "Cannot delete goal!!!";
            log.info(message);
            throw new ServiceRuntimeException(message);
        }
    }

    @Transactional
    @Override
    public void update(Goal goal) {
        goalRepository.update(goal.getId(),
                goal.getTitle(),
                goal.getCategory(),
                goal.getEstimate(),
                goal.getDaysPassed(),
                goal.getStatus(),
                userMapper.mapEntityFromDomain(goal.getUser()));
    }

    @Override
    public Goal findById(Integer id) {
        return goalMapper.mapDomainFromEntity(goalRepository.findById(id).orElse(null));
    }
}
