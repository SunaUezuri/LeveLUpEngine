package com.levelup.game.controller;

import com.levelup.game.dto.reward.RedemptionResponseDto;
import com.levelup.game.dto.reward.RewardSelectDto;
import com.levelup.game.dto.user.UserProfileDto;
import com.levelup.game.model.User;
import com.levelup.game.service.GameService;
import com.levelup.game.service.RewardRedemptionService;
import com.levelup.game.service.RewardService;
import com.levelup.game.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final RewardService rewardService;
    private final RewardRedemptionService redemptionService;
    private final UserService userService;
    private final GameService gameService;

    @GetMapping
    public String catalog(Model model, Authentication authentication) {
        String email = authentication.getName();

        UserProfileDto userProfile = gameService.getUserProfile(email);

        List<RewardSelectDto> products = rewardService.findAllAvailable();

        model.addAttribute("user", userProfile);
        model.addAttribute("products", products);
        model.addAttribute("activePage", "shop");
        return "shop/catalog";
    }

    @GetMapping("/history")
    public String history(Model model, Authentication authentication) {
        User user = userService.findEntityByEmail(authentication.getName());

        List<RedemptionResponseDto> history = redemptionService.getHistoryByUserId(user.getId());

        model.addAttribute("history", history);
        model.addAttribute("activePage", "shop");
        return "shop/history";
    }
}
