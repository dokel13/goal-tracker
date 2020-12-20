package com.tracker.goal.service;

import com.tracker.goal.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(User user);

    User findById(String id);
}
