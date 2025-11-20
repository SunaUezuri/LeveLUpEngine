package com.levelup.game.dto.reward;

import java.time.LocalDateTime;

public record RedemptionResponseDto(
        Long id,
        String rewardName,
        Integer pointsSpent,
        LocalDateTime redeemedAt
) {}
