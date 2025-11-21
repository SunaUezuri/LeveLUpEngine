package com.levelup.game.dto.user;

public record UserResponseDto(
        Long id,
        String fullName,
        String email,
        Integer pointBalance,
        String jobTitle,
        String teamName,
        String role,
        Character isActive
) {}