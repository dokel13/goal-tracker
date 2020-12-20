package com.tracker.goal.domain;

public enum GoalStatus {
    NEW("new"), IN_PROGRESS("in_progress"), COMPLETED("completed");

    private String status;

    GoalStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
