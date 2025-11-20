package com.levelup.game.mapper;

import com.levelup.game.dto.completion.CompletionHistoryDto;
import com.levelup.game.model.TaskCompletion;
import org.springframework.stereotype.Component;

@Component
public class TaskCompletionMapper {

    public CompletionHistoryDto toDto(TaskCompletion entity) {
        if (entity == null) return null;

        return new CompletionHistoryDto(
                entity.getId(),
                entity.getTask().getTitle(),
                entity.getTask().getPointsValue(),
                entity.getTask().getTaskType(),
                entity.getCompletedAt()
        );
    }
}
