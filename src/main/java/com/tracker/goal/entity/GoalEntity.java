package com.tracker.goal.entity;

import com.tracker.goal.domain.GoalCategory;
import com.tracker.goal.domain.GoalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goals")
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
    @Enumerated(EnumType.STRING)
    private GoalStatus status;

    @Column(name = "category", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private GoalCategory category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    public GoalEntity(String title, Integer estimate, Integer daysPassed, LocalDateTime creationDate,
                      GoalStatus status, GoalCategory category, UserEntity user) {
        this.title = title;
        this.estimate = estimate;
        this.daysPassed = daysPassed;
        this.creationDate = creationDate;
        this.status = status;
        this.category = category;
        this.user = user;
    }

    @Override
    public String toString() {
        return "GoalEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", estimate=" + estimate +
                ", daysPassed=" + daysPassed +
                ", creationDate=" + creationDate +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
