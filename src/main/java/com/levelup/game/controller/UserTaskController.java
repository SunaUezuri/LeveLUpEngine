package com.levelup.game.controller;

import com.levelup.game.dto.assignment.AssignmentResponseDto;
import com.levelup.game.dto.completion.CompleteTaskDto;
import com.levelup.game.model.User;
import com.levelup.game.service.TaskAssignmentService;
import com.levelup.game.service.TaskCompletionService;
import com.levelup.game.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class UserTaskController {

    private final TaskAssignmentService assignmentService;
    private final TaskCompletionService completionProducer; // Producer RabbitMQ
    private final UserService userService;

    @GetMapping("/my-tasks")
    public String myTasks(Model model, Authentication authentication) {
        // 1. Identifica quem está logado
        String email = authentication.getName();
        User user = userService.findEntityByEmail(email);

        // Descobre o time do usuário (pode ser nulo)
        Long teamId = (user.getTeam() != null) ? user.getTeam().getId() : null;

        // 2. Busca tarefas atribuídas a ele (Individual) OU ao time dele (Equipe)
        List<AssignmentResponseDto> assignments = assignmentService.findMyAssignments(user.getId(), teamId);

        model.addAttribute("assignments", assignments);
        model.addAttribute("activePage", "my-tasks");

        return "tasks/my-tasks";
    }

    @PostMapping("/complete")
    public String completeTask(@RequestParam Long taskId,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            String email = authentication.getName();
            User user = userService.findEntityByEmail(email);

            CompleteTaskDto msgDto = new CompleteTaskDto(user.getId(), taskId);

            completionProducer.completeTask(msgDto);

            redirectAttributes.addFlashAttribute("successMessage",
                    "🚀 Boa! Sua missão foi enviada para validação. Seus pontos cairão em breve!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao processar missão: " + e.getMessage());
        }

        return "redirect:/tasks/my-tasks";
    }
}
