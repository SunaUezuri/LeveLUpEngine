package com.levelup.game.dto.user;

public record UserProfileDto(
        Long userId,
        String fullName,
        String email,
        String teamName,
        String jobTitle,
        Integer currentBalance,
        Integer totalXp,
        String levelName,
        String levelBadgeUrl,
        Integer nextLevelXp,
        Integer progressPercent
) {}
