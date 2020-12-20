package com.tracker.goal.service.mapper;

import com.tracker.goal.domain.Goal;
import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.entity.GoalEntity;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.DatabaseRuntimeException;
import com.tracker.goal.exception.ServiceRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class GoalMapper {

    public Goal mapDomainFromEntity(GoalEntity entity) {
        try {
            return isNull(entity) ? null : Goal.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .creationDate(entity.getCreationDate())
                    .estimate(entity.getEstimate())
                    .daysPassed(entity.getDaysPassed())
                    .status(entity.getStatus())
                    .category(entity.getCategory())
                    .user(User.builder().id(entity.getId()).build())
                    .build();
        } catch (Exception exception) {
            String message = "Goal mapping exception!";
            log.debug(message, exception);
            throw new DatabaseRuntimeException(exception, message);
        }
    }

    public GoalEntity mapEntityFromDomain(Goal goal, UserEntity user) {
        try {
            return isNull(user) ? null : new GoalEntity(goal.getTitle(),
                    goal.getEstimate(),
                    goal.getDaysPassed(),
                    goal.getCreationDate(),
                    goal.getStatus(),
                    goal.getCategory(),
                    user);
        } catch (Exception exception) {
            String message = "GoalEntity mapping exception!";
            log.debug(message, exception);
            throw new ServiceRuntimeException(exception, message);
        }
    }
}
