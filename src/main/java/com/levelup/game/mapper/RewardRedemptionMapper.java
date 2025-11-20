package com.levelup.game.mapper;

import com.levelup.game.dto.reward.RedemptionResponseDto;
import com.levelup.game.model.RewardRedemption;
import org.springframework.stereotype.Component;

@Component
public class RewardRedemptionMapper {

    public RedemptionResponseDto toDto(RewardRedemption entity) {
        if (entity == null) return null;

        return new RedemptionResponseDto(
                entity.getId(),
                entity.getReward().getName(),
                entity.getPointsSpent(),
                entity.getRedeemedAt()
        );
    }
}
