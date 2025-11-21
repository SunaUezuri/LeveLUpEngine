package com.levelup.game.dto.assignment;

import java.time.LocalDate;

public record AssignmentResponseDto(
        Long id,
        Long taskId,
        String taskTitle,
        String assignedToName,
        String taskDescription,
        Integer pointsValue,
        String taskType,
        String type,
        LocalDate periodStart,
        LocalDate periodEnd,
        Boolean isMandatory,
        Boolean isCompleted,
        String statusBadge
) {}
