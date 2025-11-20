package com.levelup.game.service;

import com.levelup.game.dto.reward.RewardResponseDto;
import com.levelup.game.dto.reward.RewardSelectDto;
import com.levelup.game.mapper.RewardMapper;
import com.levelup.game.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;
    private final RewardMapper rewardMapper;

    // Lista completa para o Admin
    @Transactional(readOnly = true)
    public List<RewardResponseDto> findAll() {
        return rewardRepository.findAll().stream()
                .map(rewardMapper::toDto)
                .collect(Collectors.toList());
    }

    // Lista filtrada para o usuário ver o que pode comprar
    @Transactional(readOnly = true)
    public List<RewardSelectDto> findAllAvailable() {
        return rewardRepository.findByIsActiveAndStockQuantityGreaterThan('Y', 0).stream()
                .map(rewardMapper::toSelectDto)
                .collect(Collectors.toList());
    }
}
