package com.levelup.game.dto.reward;

public record RewardResponseDto(
        Long id,
        String name,
        String description,
        Integer pointCost,
        Integer stockQuantity,
        Character isActive
) {}
