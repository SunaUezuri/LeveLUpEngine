package com.levelup.game.mapper;

import com.levelup.game.dto.reward.RewardResponseDto;
import com.levelup.game.dto.reward.RewardSelectDto;
import com.levelup.game.model.Reward;
import org.springframework.stereotype.Component;

@Component
public class RewardMapper {

    public RewardResponseDto toDto(Reward entity) {
        if (entity == null) return null;

        return new RewardResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPointCost(),
                entity.getStockQuantity(),
                entity.getIsActive()
        );
    }

    public RewardSelectDto toSelectDto(Reward entity) {
        if (entity == null) return null;

        return new RewardSelectDto(
                entity.getId(),
                entity.getName(),
                entity.getPointCost(),
                entity.getStockQuantity()
        );
    }
}
