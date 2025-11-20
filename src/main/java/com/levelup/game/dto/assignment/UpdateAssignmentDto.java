package com.levelup.game.dto.assignment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateAssignmentDto(
        @NotNull Long id,
        @NotNull LocalDate periodStart,
        @NotNull @Future LocalDate periodEnd,
        Boolean isMandatory
) {}
