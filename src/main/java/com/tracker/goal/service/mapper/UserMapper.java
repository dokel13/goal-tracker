package com.tracker.goal.service.mapper;

import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.DatabaseRuntimeException;
import com.tracker.goal.exception.ServiceRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class UserMapper {

    public User mapDomainFromEntity(UserEntity entity) {
        try {
            return isNull(entity) ? null : User.builder()
                    .role(Role.valueOf(entity
                            .getRole()))
                    .email(entity.getEmail())
                    .password(entity.getPassword())
                    .name(entity.getName())
                    .id(entity.getId())
                    .build();
        } catch (Exception exception) {
            String message = "User mapping exception!";
            log.debug(message, exception);
            throw new DatabaseRuntimeException(exception, message);
        }
    }

    public UserEntity mapEntityFromDomain(User user) {
        try {
            return isNull(user) ? null : new UserEntity(user.getEmail(),
                    user.getPassword(),
                    user.getName(),
                    user.getRole().name());
        } catch (Exception exception) {
            String message = "UserEntity mapping exception!";
            log.debug(message, exception);
            throw new ServiceRuntimeException(exception, message);
        }
    }
}
