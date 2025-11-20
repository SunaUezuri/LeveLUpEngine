package com.levelup.game.dto.completion;

import jakarta.validation.constraints.NotNull;

public record CompleteTaskDto(
        @NotNull Long userId,
        @NotNull Long taskId
) {}