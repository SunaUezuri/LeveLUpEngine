package com.levelup.game.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_LEVELUP_TASK_COMPLETIONS")
public class TaskCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPLETION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TASK_ID", nullable = false)
    private Task task;

    @Column(name = "COMPLETED_AT", nullable = false)
    private LocalDateTime completedAt;
}