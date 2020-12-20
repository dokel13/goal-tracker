package com.tracker.goal.controller.utils;

import com.tracker.goal.domain.User;
import com.tracker.goal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public abstract class UserGetter {

    @Autowired
    protected UserService userService;

    protected User getUserFromContext() {
        String username = getContext().getAuthentication().getName();

        return (User) userService.loadUserByUsername(username);
    }
}
