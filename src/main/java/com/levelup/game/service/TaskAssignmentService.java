package com.levelup.game.service;

import com.levelup.game.dto.assignment.AssignmentResponseDto;
import com.levelup.game.dto.assignment.CreateAssignmentDto;
import com.levelup.game.mapper.TaskAssignmentMapper;
import com.levelup.game.model.TaskAssignment;
import com.levelup.game.repository.TaskAssignmentRepository;
import com.levelup.game.repository.TaskCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskAssignmentService {

    private final TaskAssignmentRepository assignmentRepository;
    private final TaskCompletionRepository completionRepository;
    private final TaskAssignmentMapper assignmentMapper;

    @Transactional(readOnly = true)
    public List<AssignmentResponseDto> findAll() {

        List<TaskAssignment> assignments = assignmentRepository.findAll();

        return assignments.stream()
                .map(entity -> {
                    AssignmentResponseDto dto = assignmentMapper.toDto(entity);

                    boolean isCompleted = false;

                    if (entity.getUser() != null) {
                        isCompleted = completionRepository.existsByUser_IdAndTask_Id(
                                entity.getUser().getId(),
                                entity.getTask().getId()
                        );
                    }
                    return new AssignmentResponseDto(
                            dto.id(),
                            entity.getTask().getId(),
                            dto.taskTitle(),
                            dto.assignedToName(),
                            dto.taskDescription(),
                            dto.pointsValue(),
                            dto.taskType(),
                            dto.type(),
                            dto.periodStart(),
                            dto.periodEnd(),
                            dto.isMandatory(),
                            isCompleted,
                            isCompleted ? "bg-success" : dto.statusBadge()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AssignmentResponseDto> findMyAssignments(Long userId, Long teamId) {
        return assignmentRepository.findMyActiveAssignments(userId, teamId).stream()
                .map(assignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void assign(CreateAssignmentDto dto) {
        Character mandatory = (Boolean.TRUE.equals(dto.isMandatory())) ? 'Y' : 'N';

        assignmentRepository.assignTaskProcedure(
                dto.taskId(),
                dto.userId(),
                dto.teamId(),
                dto.periodStart(),
                dto.periodEnd(),
                mandatory,
                null
        );
    }

    @Transactional
    public void delete(Long id) {
        assignmentRepository.deleteById(id);
    }
}
