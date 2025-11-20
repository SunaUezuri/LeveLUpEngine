package com.levelup.game.dto.task;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        Integer pointsValue,
        String taskType,
        Character isActive,
        String statusLabel
) {}
