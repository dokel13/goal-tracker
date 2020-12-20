package com.tracker.goal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal {

    private int id;

    @NotEmpty
    private String title;

    @NotEmpty
    private Integer estimate;

    private Integer daysPassed;

    @NotEmpty
    private LocalDateTime creationDate;

    private String status;

    private GoalCategory category;

    @NotEmpty
    @JsonIgnore
    private User user;
}
