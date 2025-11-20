package com.levelup.game.dto.task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTaskDto(
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 255, message = "Máximo de 255 caracteres")
        String title,

        @Size(max = 1000, message = "Máximo de 1000 caracteres")
        String description,

        @NotNull(message = "A pontuação é obrigatória")
        @Min(value = 1, message = "A pontuação deve ser maior que zero")
        Integer pointsValue,

        @NotBlank(message = "Selecione o tipo da tarefa")
        String taskType // 'WORK', 'SOCIAL', 'WELLNESS'
) {}
