package com.tracker.goal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "badges")
public class BadgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id", nullable = false)
    private int id;


    @Column(name = "link", unique = true, nullable = false, length = 300)
    private String link;

    @ManyToMany(mappedBy = "badges", fetch = FetchType.LAZY)
    private List<UserEntity> users;
}
