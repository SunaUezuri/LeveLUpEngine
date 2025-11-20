package com.levelup.game.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_LEVELUP_TASK_ASSIGNMENTS")
public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSIGNMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TASK_ID", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "PERIOD_START", nullable = false)
    private LocalDate periodStart;

    @Column(name = "PERIOD_END", nullable = false)
    private LocalDate periodEnd;

    @Column(name = "IS_MANDATORY", nullable = false)
    private Character isMandatory = 'N';
}