package com.tracker.goal.service.mapper;

import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.DatabaseRuntimeException;
import com.tracker.goal.exception.ServiceRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class UserMapper {

    public static User mapDomainFromEntity(UserEntity entity) {
        try {
            return isNull(entity) ? null : User.builder()
                    .role(Role.valueOf(entity
                            .getRole()))
                    .email(entity.getEmail())
                    .password(entity.getPassword())
                    .name(entity.getName())
                    .id(entity.getId())
                    .friends(entity.getFriends().stream()
                            .map(UserMapper::mapDomainFromEntity)
                            .peek(user -> user.setFriends(null))
                            .collect(Collectors.toList()))
                    .build();
        } catch (Exception exception) {
            String message = "User mapping exception!";
            log.debug(message, exception);
            throw new DatabaseRuntimeException(exception, message);
        }
    }

    public UserEntity mapEntityFromDomain(User user) {
        try {
            return isNull(user) ? null : returnEntity(user);
        } catch (Exception exception) {
            String message = "UserEntity mapping exception!";
            log.debug(message, exception);
            throw new ServiceRuntimeException(exception, message);
        }
    }

    private UserEntity returnEntity(User user) {
        if (user.getId() == null) {
            return new UserEntity(user.getEmail(),
                    user.getPassword(),
                    user.getName(),
                    user.getRole().name());
        } else {
            return new UserEntity(user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getName(),
                    user.getRole().name());
        }
    }
}
