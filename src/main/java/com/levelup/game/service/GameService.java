package com.levelup.game.service;

import com.levelup.game.dto.rank.RankingEntryDto;
import com.levelup.game.dto.user.UserProfileDto;
import com.levelup.game.model.User;
import com.levelup.game.repository.TaskCompletionRepository;
import com.levelup.game.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final UserRepository userRepository;
    private final TaskCompletionRepository completionRepository;

    // Lógica do negócio: Cálculo de nível do perfil

    /*
      Define o nível geek baseado no XP Total.
    */
    public String calculateLevelName(Integer totalXp) {
        if (totalXp == null) totalXp = 0;

        if (totalXp < 100) return "Noob";
        if (totalXp < 500) return "Padawan";
        if (totalXp < 1500) return "Ranger";
        if (totalXp < 3000) return "Jedi Knight";
        if (totalXp < 5000) return "Wizard";
        return "Cyberpunk Legend";
    }

    /*
      Define o próximo marco de XP para a barra de progresso.
    */
    public Integer calculateNextLevelXp(Integer totalXp) {
        if (totalXp == null) totalXp = 0;

        if (totalXp < 100) return 100;
        if (totalXp < 500) return 500;
        if (totalXp < 1500) return 1500;
        if (totalXp < 3000) return 3000;
        if (totalXp < 5000) return 5000;
        return 10000;
    }

    // Gera uma URL para a imagem da badge
    public String getLevelBadgeUrl(String levelName) {
        if (levelName == null) return "/images/badges/noob.png";

        String filename = levelName.toLowerCase()
                .replace(" ", "_")
                .replace("ã", "a");

        return "/images/badges/" + filename + ".png";
    }

    // Método para calcular a porcentagem para a barra de progresso
    public Integer calculateProgressPercent(Integer totalXp) {
        if (totalXp == null) totalXp = 0;
        int target = calculateNextLevelXp(totalXp);

        if (target == 0) return 100;

        double percent = ((double) totalXp / target) * 100;

        return (int) Math.min(percent, 100);
    }

    // Métodos de montagem

    @Transactional(readOnly = true)
    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + email));

        Integer totalXp = completionRepository.getTotalPointsEarned(user.getId());
        if (totalXp == null) totalXp = 0;

        String levelName = calculateLevelName(totalXp);

        return new UserProfileDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                (user.getTeam() != null) ? user.getTeam().getTeamName() : "Ronin (Sem Time)",
                user.getJobTitle(),
                user.getPointBalance(),
                totalXp,
                levelName,
                getLevelBadgeUrl(levelName),
                calculateNextLevelXp(totalXp),
                calculateProgressPercent(totalXp)
        );
    }

    @Transactional(readOnly = true)
    public List<RankingEntryDto> getLeaderboard() {
        List<User> users = userRepository.findAll();
        List<RankingEntryDto> ranking = new ArrayList<>();

        for (User user : users) {
            if (user.getIsActive() == 'N') continue;

            Integer xp = completionRepository.getTotalPointsEarned(user.getId());
            if (xp == null) xp = 0;

            String teamName = (user.getTeam() != null) ? user.getTeam().getTeamName() : "-";

            ranking.add(new RankingEntryDto(
                    0,
                    user.getFullName(),
                    teamName,
                    xp,
                    calculateLevelName(xp)
            ));
        }

        ranking.sort(Comparator.comparingInt(RankingEntryDto::totalXp).reversed());

        List<RankingEntryDto> finalRanking = new ArrayList<>();
        for (int i = 0; i < ranking.size(); i++) {
            RankingEntryDto entry = ranking.get(i);
            finalRanking.add(new RankingEntryDto(
                    i + 1,
                    entry.userName(),
                    entry.teamName(),
                    entry.totalXp(),
                    entry.levelName()
            ));
        }

        return finalRanking;
    }
}
