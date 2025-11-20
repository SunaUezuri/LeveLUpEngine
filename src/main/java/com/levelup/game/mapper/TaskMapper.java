package com.levelup.game.mapper;

import com.levelup.game.dto.task.TaskResponseDto;
import com.levelup.game.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponseDto toDto(Task task){
        if (task == null) return null;

        String statusLabel = (task.getIsActive() == 'Y') ? "Ativo" : "Inativo";

        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPointsValue(),
                task.getTaskType(),
                task.getIsActive(),
                statusLabel
        );
    }
}
