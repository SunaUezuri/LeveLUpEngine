package com.levelup.game.controller;

import com.levelup.game.dto.user.UserResponseDto;
import com.levelup.game.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        // Busca todos os usuários (Service apenas de leitura)
        List<UserResponseDto> users = userService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("activePage", "admin-users"); // Para destacar na Sidebar

        return "admin/users/list";
    }
}
