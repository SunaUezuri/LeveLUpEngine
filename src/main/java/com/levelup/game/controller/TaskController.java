package com.levelup.game.controller;

import com.levelup.game.dto.task.CreateTaskDto;
import com.levelup.game.dto.task.TaskResponseDto;
import com.levelup.game.dto.task.UpdateTaskDto;
import com.levelup.game.service.AiTaskService;
import com.levelup.game.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final AiTaskService aiTaskService;

    // --- LISTAGEM ---
    @GetMapping
    public String listTasks(Model model) {
        List<TaskResponseDto> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("activePage", "admin-tasks"); // Para destacar no menu
        return "admin/tasks/list";
    }

    // --- CRIAÇÃO ---
    @GetMapping("/new")
    public String newTaskForm(Model model) {
        // Envia um DTO vazio para o formulário preencher
        model.addAttribute("taskDto", new CreateTaskDto(null, null, 50, "WORK"));
        model.addAttribute("isEdit", false);
        model.addAttribute("activePage", "admin-tasks");
        return "admin/tasks/form";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("taskDto") CreateTaskDto dto,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "admin/tasks/form";
        }

        try {
            taskService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa criada com sucesso!");
            return "redirect:/admin/tasks";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao criar tarefa: " + e.getMessage());
            model.addAttribute("isEdit", false);
            return "admin/tasks/form";
        }
    }

    // --- EDIÇÃO ---
    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        TaskResponseDto existingTask = taskService.findById(id);

        // Converte ResponseDto para UpdateDto para popular o form
        UpdateTaskDto updateDto = new UpdateTaskDto(
                existingTask.id(),
                existingTask.title(),
                existingTask.description(),
                existingTask.pointsValue(),
                existingTask.taskType(),
                existingTask.isActive()
        );

        model.addAttribute("taskDto", updateDto);
        model.addAttribute("isEdit", true);
        model.addAttribute("activePage", "admin-tasks");
        return "admin/tasks/form";
    }

    @PostMapping("/update")
    public String updateTask(@Valid @ModelAttribute("taskDto") UpdateTaskDto dto,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "admin/tasks/form";
        }

        try {
            taskService.update(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa atualizada com sucesso!");
            return "redirect:/admin/tasks";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao atualizar: " + e.getMessage());
            model.addAttribute("isEdit", true);
            return "admin/tasks/form";
        }
    }

    // --- DELEÇÃO (Soft Delete) ---
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            taskService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa desativada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao desativar: " + e.getMessage());
        }
        return "redirect:/admin/tasks";
    }

    // --- API AJAX PARA IA (Chamado pelo JavaScript da tela) ---
    @PostMapping("/generate-ai")
    @ResponseBody // Retorna JSON, não HTML
    public ResponseEntity<CreateTaskDto> generateAiTask(@RequestBody Map<String, String> payload) {
        String topic = payload.get("topic");
        CreateTaskDto suggestion = aiTaskService.generateTaskSuggestion(topic);
        return ResponseEntity.ok(suggestion);
    }

    @PostMapping("/analyze-ai")
    @ResponseBody
    public ResponseEntity<CreateTaskDto> analyzeTask(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        String description = payload.get("description");

        if (title == null || description == null || title.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        CreateTaskDto estimation = aiTaskService.analyzeAndEstimatePoints(title, description);
        return ResponseEntity.ok(estimation);
    }
}
