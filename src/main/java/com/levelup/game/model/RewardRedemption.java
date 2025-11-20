package com.levelup.game.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_LEVELUP_REWARD_REDEMPTIONS")
public class RewardRedemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REDEMPTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "REWARD_ID", nullable = false)
    private Reward reward;

    @Column(name = "POINTS_SPENT", nullable = false)
    private Integer pointsSpent;

    @Column(name = "REDEEMED_AT", nullable = false)
    private LocalDateTime redeemedAt;
}