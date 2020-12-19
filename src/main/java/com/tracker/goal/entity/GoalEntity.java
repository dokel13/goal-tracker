package com.tracker.goal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goal")
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "estimate", nullable = false, length = 5)
    private Integer estimate;

    @Column(name = "days_passed", nullable = false, length = 5)
    private Integer daysPassed;

    @Column(name = "creation_date", nullable = false, length = 30)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;
}