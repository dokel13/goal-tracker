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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_badges", joinColumns = {
            @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "badge", referencedColumnName = "badge_id", nullable = false, updatable = false)})

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalEntity> goals;

    public UserEntity(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public UserEntity(String email) {
        this.email = email;
    }
}
