package com.levelup.game.service;

import com.levelup.game.dto.user.UserResponseDto;
import com.levelup.game.dto.user.UserSelectDto;
import com.levelup.game.exception.UserNotFoundException;
import com.levelup.game.mapper.UserMapper;
import com.levelup.game.model.User;
import com.levelup.game.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserSelectDto> findAllActiveForSelect() {
        return userRepository.findAll().stream()
                .filter(u -> u.getIsActive() == 'Y')
                .map(userMapper::toSelectDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public User findEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }
}
