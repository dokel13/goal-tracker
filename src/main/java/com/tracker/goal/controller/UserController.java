package com.tracker.goal.controller;

import com.tracker.goal.controller.utils.UserGetter;
import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController extends UserGetter {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @PostMapping("/register")
    public void register(@Validated @RequestBody User newUser) {
        userService.register(User.builder()
                .role(Role.USER)
                .email(newUser.getEmail())
                .name(newUser.getName())
                .password(newUser.getPassword())
                .build());
    }

    @GetMapping("/profile/me")
    public User current() {
        return getUserFromContext();
    }

    @GetMapping("/user/badges")
    public List<String> getUserBadges() {
        return userService.geBadgesByUserId(getUserFromContext().getId());
    }

    @PostMapping("/friends/{friendId}")
    public void addFriend(@PathVariable Integer friendId) {
        userService.addFriend(getUserFromContext().getId(), friendId);
    }
}
