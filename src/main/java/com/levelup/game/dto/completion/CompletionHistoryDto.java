package com.levelup.game.dto.completion;

import java.time.LocalDateTime;

public record CompletionHistoryDto(
        Long id,
        String taskTitle,
        Integer pointsEarned,
        String taskType,
        LocalDateTime completedAt
) {}
