package com.levelup.game.controller;

import com.levelup.game.dto.rank.RankingEntryDto;
import com.levelup.game.dto.user.UserProfileDto;
import com.levelup.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String userEmail = authentication.getName();

        UserProfileDto userProfile = gameService.getUserProfile(userEmail);

        List<RankingEntryDto> leaderboard = gameService.getLeaderboard();

        model.addAttribute("user", userProfile);
        model.addAttribute("leaderboard", leaderboard);

        model.addAttribute("activePage", "dashboard");

        return "dashboard";
    }

    // Redireciona a raiz para o dashboard
    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }
}
