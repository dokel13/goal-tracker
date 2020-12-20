package com.tracker.goal.controller;

import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.service.GoalService;
import com.tracker.goal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController {

    private UserService userService;
    private GoalService goalService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@Validated @RequestBody User newUser) {
        User user = userService.register(User.builder()
                .role(Role.USER)
                .email(newUser.getEmail())
                .name(newUser.getName())
                .password(newUser.getPassword())
                .build());

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getEmail(), newUser.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        getContext().setAuthentication(auth);

        return user;
    }

    @GetMapping("profile/me")
    public User current() {
        String username = getContext().getAuthentication().getName();
        UserDetails userDetails = userService.loadUserByUsername(username);
        return (User) userDetails;
    }

    @GetMapping("/goals")
    public List<Goal> getGoals(){
        String username = getContext().getAuthentication().getName();
        User user = (User) userService.loadUserByUsername(username);
        Integer userId = user.getId();
        List<Goal> goals = goalService.findAllByUserId(userId);
        System.out.println(goals);
        return goals;
    }

    @PostMapping("/goals")
    public Goal createGoal(@RequestBody Goal goal){

        return null;
    }
}
