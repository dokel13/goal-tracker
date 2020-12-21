package com.tracker.goal.service;

import com.tracker.goal.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(User user);

    List<String> geBadgesByUserId(Integer id);

    void addFriend(Integer userId, Integer friendId);
}
