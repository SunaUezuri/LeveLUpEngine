package com.levelup.game.service;

import com.levelup.game.dto.team.TeamResponseDto;
import com.levelup.game.dto.team.TeamSelectDto;
import com.levelup.game.mapper.TeamMapper;
import com.levelup.game.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    // Tabela de times do Admin
    @Transactional(readOnly = true)
    public List<TeamResponseDto> findAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    // Dropdowns
    @Transactional(readOnly = true)
    public List<TeamSelectDto> findAllForSelect() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toSelectDto)
                .collect(Collectors.toList());
    }
}
