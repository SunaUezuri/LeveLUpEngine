package com.levelup.game.service;

import com.levelup.game.dto.assignment.AssignmentResponseDto;
import com.levelup.game.dto.assignment.CreateAssignmentDto;
import com.levelup.game.dto.assignment.UpdateAssignmentDto;
import com.levelup.game.exception.ResourceNotFoundException;
import com.levelup.game.mapper.TaskAssignmentMapper;
import com.levelup.game.model.TaskAssignment;
import com.levelup.game.repository.TaskAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskAssignmentService {

    private final TaskAssignmentRepository assignmentRepository;
    private final TaskAssignmentMapper assignmentMapper;

    @Transactional(readOnly = true)
    public List<AssignmentResponseDto> findAll() {
        return assignmentRepository.findAll().stream()
                .map(assignmentMapper::toDto)
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
                null // Output ID
        );
    }

    @Transactional
    public void update(UpdateAssignmentDto dto) {
        TaskAssignment assignment = assignmentRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Atribuição não encontrada"));

        assignment.setPeriodStart(dto.periodStart());
        assignment.setPeriodEnd(dto.periodEnd());

        if (dto.isMandatory() != null) {
            assignment.setIsMandatory(dto.isMandatory() ? 'Y' : 'N');
        }

        assignmentRepository.save(assignment);
    }

    @Transactional
    public void delete(Long id) {
        assignmentRepository.deleteById(id);
    }
}
