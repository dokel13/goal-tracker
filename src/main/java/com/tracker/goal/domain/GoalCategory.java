package com.tracker.goal.domain;

public enum GoalCategory {
    SPORT("sport"), HEALTH("health"), ECOLOGY("ecology"), OTHER("other");

    private String category;

    GoalCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
