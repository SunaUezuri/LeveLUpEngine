package com.levelup.game.controller;

import com.levelup.game.dto.assignment.AssignmentResponseDto;
import com.levelup.game.dto.assignment.CreateAssignmentDto;
import com.levelup.game.dto.task.TaskResponseDto;
import com.levelup.game.dto.team.TeamSelectDto;
import com.levelup.game.dto.user.UserSelectDto;
import com.levelup.game.service.TaskAssignmentService;
import com.levelup.game.service.TaskService;
import com.levelup.game.service.TeamService;
import com.levelup.game.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final TaskAssignmentService assignmentService;
    private final TaskService taskService;
    private final UserService userService;
    private final TeamService teamService;

    // --- LISTAGEM ---
    @GetMapping
    public String listAssignments(Model model) {
        List<AssignmentResponseDto> assignments = assignmentService.findAll();
        model.addAttribute("assignments", assignments);
        model.addAttribute("activePage", "admin-assignments");
        return "admin/assignments/list";
    }

    // --- CRIAÇÃO ---
    @GetMapping("/new")
    public String newAssignmentForm(Model model) {
        // Prepara os dados para os Dropdowns
        populateFormAttributes(model);

        // DTO vazio com datas padrão
        CreateAssignmentDto dto = new CreateAssignmentDto(
                null, null, null,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                false
        );

        model.addAttribute("assignmentDto", dto);
        model.addAttribute("activePage", "admin-assignments");
        return "admin/assignments/form";
    }

    @PostMapping("/save")
    public String saveAssignment(@Valid @ModelAttribute("assignmentDto") CreateAssignmentDto dto,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        // Validação customizada: Ou User ou Team deve ser preenchido (XOR)
        if (dto.userId() == null && dto.teamId() == null) {
            result.rejectValue("userId", "error.user", "Selecione um Usuário OU um Time.");
        }

        if (result.hasErrors()) {
            populateFormAttributes(model); // Recarrega listas se der erro
            return "admin/assignments/form";
        }

        try {
            assignmentService.assign(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa atribuída com sucesso!");
            return "redirect:/admin/assignments";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao atribuir: " + e.getMessage());
            populateFormAttributes(model);
            return "admin/assignments/form";
        }
    }

    // --- DELEÇÃO (Hard Delete para correções) ---
    @GetMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            assignmentService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Atribuição removida.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao remover: " + e.getMessage());
        }
        return "redirect:/admin/assignments";
    }

    // Método auxiliar para carregar as listas
    private void populateFormAttributes(Model model) {
        List<TaskResponseDto> tasks = taskService.findAllActive();
        List<UserSelectDto> users = userService.findAllActiveForSelect();
        List<TeamSelectDto> teams = teamService.findAllForSelect();

        model.addAttribute("tasks", tasks);
        model.addAttribute("users", users);
        model.addAttribute("teams", teams);
    }
}