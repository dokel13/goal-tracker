package com.tracker.goal.controller;

import com.tracker.goal.controller.utils.UserGetter;
import com.tracker.goal.domain.Goal;
import com.tracker.goal.domain.GoalCategory;
import com.tracker.goal.domain.GoalStatus;
import com.tracker.goal.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RequestMapping("/api/users/goals")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class GoalController extends UserGetter {

    private GoalService goalService;

    @GetMapping
    public List<Goal> getGoals() {
        return goalService.findAllByUserId(getUserFromContext().getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createGoal(@RequestBody Goal newGoal) {
        goalService.save(Goal.builder()
                .category(newGoal.getCategory())
                .status(GoalStatus.NEW)
                .title(newGoal.getTitle())
                .estimate(newGoal.getEstimate())
                .daysPassed(0)
                .creationDate(now())
                .user(getUserFromContext())
                .build());
    }

    @DeleteMapping("/{goalId}")
    public void delete(@PathVariable Integer goalId){
        Integer userId = getUserFromContext().getId();
        goalService.delete(goalId, userId);
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
