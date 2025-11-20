package com.levelup.game.dto.assignment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateAssignmentDto(
        @NotNull(message = "A tarefa é obrigatória")
        Long taskId,

        Long userId,
        Long teamId,

        @NotNull(message = "Início é obrigatório")
        @FutureOrPresent(message = "A data não pode ser no passado")
        LocalDate periodStart,

        @NotNull(message = "Fim é obrigatório")
        @Future(message = "A data deve ser futura")
        LocalDate periodEnd,

        Boolean isMandatory
) {}
