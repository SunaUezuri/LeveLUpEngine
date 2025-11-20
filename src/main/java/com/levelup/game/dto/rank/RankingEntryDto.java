package com.levelup.game.dto.rank;

public record RankingEntryDto(
        Integer position,
        String userName,
        String teamName,
        Integer totalXp,
        String levelName
) {}
