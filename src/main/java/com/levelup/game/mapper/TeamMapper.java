package com.levelup.game.mapper;

import com.levelup.game.dto.team.TeamResponseDto;
import com.levelup.game.dto.team.TeamSelectDto;
import com.levelup.game.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamResponseDto toDto(Team team) {
        if (team == null) return null;

        return new TeamResponseDto(
                team.getId(),
                team.getTeamName()
        );
    }

    public TeamSelectDto toSelectDto(Team team) {
        if (team == null) return null;


        return new TeamSelectDto(
                team.getId(),
                team.getTeamName()
        );
    }
}
