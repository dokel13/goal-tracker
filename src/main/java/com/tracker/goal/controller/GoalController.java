package com.tracker.goal.controller;

import com.tracker.goal.controller.utils.UserGetter;
import com.tracker.goal.domain.Goal;
import com.tracker.goal.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users/goals")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class GoalController extends UserGetter {

    private GoalService goalService;

    @GetMapping
    public List<Goal> getGoals() {
        return goalService.findAllByUserId(getUserFromContext().getId());
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal){

        return null;
    }

    @GetMapping("/status/{status}")
    public List<Goal> getByStatus(@PathVariable("status") String status) {
        return goalService.findByStatusAndUser(status, getUserFromContext().getId());
    }

    @GetMapping("/category/{category}")
    public List<Goal> getByCategory(@PathVariable("category") String category) {
        return goalService.findByCategoryAndUser(category, getUserFromContext().getId());
    }
}
