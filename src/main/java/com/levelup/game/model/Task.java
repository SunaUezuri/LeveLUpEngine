package com.levelup.game.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_LEVELUP_TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "POINTS_VALUE", nullable = false)
    private Integer pointsValue;

    @Column(name = "TASK_TYPE", nullable = false)
    private String taskType;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Character isActive = 'Y';

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}