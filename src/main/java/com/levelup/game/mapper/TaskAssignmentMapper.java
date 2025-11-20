package com.levelup.game.mapper;

import com.levelup.game.dto.assignment.AssignmentResponseDto;
import com.levelup.game.model.TaskAssignment;
import org.springframework.stereotype.Component;

@Component
public class TaskAssignmentMapper {

    public AssignmentResponseDto toDto(TaskAssignment entity) {
        if (entity == null) return null;

        String assigneeName = "Desconhecido";
        String type = "-";

        if (entity.getUser() != null) {
            assigneeName = entity.getUser().getFullName();
            type = "Individual";
        } else if (entity.getTeam() != null) {
            assigneeName = "Equipe: " + entity.getTeam().getTeamName();
            type = "Equipe";
        }


        String badgeClass = (entity.getIsMandatory() == 'Y') ? "bg-danger" : "bg-info";

        return new AssignmentResponseDto(
                entity.getId(),
                entity.getTask().getTitle(),
                entity.getTask().getDescription(),
                entity.getTask().getPointsValue(),
                entity.getTask().getTaskType(),
                entity.getPeriodStart(),
                entity.getPeriodEnd(),
                entity.getIsMandatory() == 'Y',
                false,
                badgeClass
        );
    }
}
