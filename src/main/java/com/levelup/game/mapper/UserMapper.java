package com.levelup.game.mapper;

import com.levelup.game.dto.user.UserResponseDto;
import com.levelup.game.dto.user.UserSelectDto;
import com.levelup.game.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toDto(User entity) {
        if (entity == null) return null;

        return new UserResponseDto(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPointBalance(),
                entity.getJobTitle(),
                (entity.getTeam() != null) ? entity.getTeam().getTeamName() : "Sem Time",
                entity.getRole(),
                entity.getIsActive()
        );
    }

    public UserSelectDto toSelectDto(User entity) {
        if (entity == null) return null;

        return new UserSelectDto(
                entity.getId(),
                entity.getFullName()
        );
    }
}
