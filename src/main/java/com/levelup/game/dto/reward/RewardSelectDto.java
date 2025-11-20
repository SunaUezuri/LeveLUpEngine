package com.levelup.game.dto.reward;

public record RewardSelectDto(
        Long id,
        String name,
        Integer pointCost,
        Integer stockQuantity
) {}
