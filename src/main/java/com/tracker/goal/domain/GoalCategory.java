package com.tracker.goal.domain;

public enum GoalCategory {
    SPORT("Sport"), HEALTH("Health"), ECOLOGY("Ecology"), OTHER("Other");

    private String category;

    GoalCategory(String category) {
        this.category = category;
    }
}
