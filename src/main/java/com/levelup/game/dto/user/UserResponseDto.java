package com.levelup.game.dto.user;

public record UserResponseDto(
        Long id,
        String fullName,
        String email,
        String jobTitle,
        String teamName,
        String role,
        Character isActive
) {}