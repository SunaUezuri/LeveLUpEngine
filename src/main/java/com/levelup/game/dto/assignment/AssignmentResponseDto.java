package com.levelup.game.dto.assignment;

import java.time.LocalDate;

public record AssignmentResponseDto(
        Long id,
        String taskTitle,
        String taskDescription,
        Integer pointsValue,
        String taskType,
        LocalDate periodStart,
        LocalDate periodEnd,
        Boolean isMandatory,
        Boolean isCompleted,
        String statusBadge
) {}
