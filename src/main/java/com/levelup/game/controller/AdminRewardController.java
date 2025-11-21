package com.levelup.game.controller;

import com.levelup.game.dto.reward.RewardResponseDto;
import com.levelup.game.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/rewards")
@RequiredArgsConstructor
public class AdminRewardController {

    private final RewardService rewardService;

    @GetMapping
    public String listRewards(Model model) {
        List<RewardResponseDto> rewards = rewardService.findAll();
        model.addAttribute("rewards", rewards);
        model.addAttribute("activePage", "admin-rewards");
        return "admin/rewards/list";
    }
}
