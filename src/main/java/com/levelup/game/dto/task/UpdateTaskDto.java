package com.levelup.game.dto.task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskDto(
        @NotNull
        Long id,

        @NotBlank(message = "O título é obrigatório")
        @Size(max = 255)
        String title,

        @Size(max = 1000)
        String description,

        @NotNull
        @Min(1)
        Integer pointsValue,

        @NotBlank
        String taskType,

        Character isActive
) {}
