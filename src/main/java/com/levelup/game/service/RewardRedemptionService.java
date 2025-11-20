package com.levelup.game.service;

import com.levelup.game.dto.reward.RedemptionResponseDto;
import com.levelup.game.mapper.RewardRedemptionMapper;
import com.levelup.game.repository.RewardRedemptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardRedemptionService {

    private final RewardRedemptionRepository redemptionRepository;
    private final RewardRedemptionMapper redemptionMapper;

    // Histórico de um usuário específico
    @Transactional(readOnly = true)
    public List<RedemptionResponseDto> getHistoryByUserId(Long userId) {
        return redemptionRepository.findByUserIdOrderByRedeemedAtDesc(userId).stream()
                .map(redemptionMapper::toDto)
                .collect(Collectors.toList());
    }

    // Histórico global (para o Admin auditar)
    @Transactional(readOnly = true)
    public List<RedemptionResponseDto> getAllRedemptions() {
        return redemptionRepository.findAll().stream()
                .map(redemptionMapper::toDto)
                .collect(Collectors.toList());
    }
}
