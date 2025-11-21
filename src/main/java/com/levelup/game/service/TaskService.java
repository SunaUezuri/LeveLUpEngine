package com.levelup.game.service;

import com.levelup.game.dto.task.CreateTaskDto;
import com.levelup.game.dto.task.TaskResponseDto;
import com.levelup.game.dto.task.UpdateTaskDto;
import com.levelup.game.exception.ResourceNotFoundException;
import com.levelup.game.mapper.TaskMapper;
import com.levelup.game.model.Task;
import com.levelup.game.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAllActive() {
        return taskRepository.findByIsActive('Y').stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskResponseDto findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
        return taskMapper.toDto(task);
    }

    @Transactional
    public void create(CreateTaskDto dto) {
        taskRepository.createTaskProcedure(
                dto.title(),
                dto.description(),
                dto.pointsValue(),
                dto.taskType(),
                null
        );
    }

    @Transactional
    public void update(UpdateTaskDto dto) {
        Task task = taskRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setPointsValue(dto.pointsValue());
        task.setTaskType(dto.taskType());

        if (dto.isActive() != null) {
            task.setIsActive(dto.isActive());
        }

        taskRepository.save(task);
    }

    @Transactional
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        // Soft Delete conforme regra do banco
        task.setIsActive('N');
        taskRepository.save(task);
    }

    @Transactional
    public void reactivate(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        task.setIsActive('Y');
        taskRepository.save(task);
    }
}
