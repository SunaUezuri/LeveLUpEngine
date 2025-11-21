package com.levelup.game.repository;

import com.levelup.game.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {

    @Query("""
           SELECT ta FROM TaskAssignment ta
           WHERE (ta.user.id = :userId OR ta.team.id = :teamId)
           AND CURRENT_DATE BETWEEN ta.periodStart AND ta.periodEnd
           AND NOT EXISTS (
               SELECT tc FROM TaskCompletion tc
               WHERE tc.task.id = ta.task.id
               AND tc.user.id = :userId
           )
           """)
    List<TaskAssignment> findMyActiveAssignments(@Param("userId") Long userId, @Param("teamId") Long teamId);

    @Procedure(procedureName = "PKG_LEVELUP_APP.PR_CREATE_TASK_ASSIGNMENT")
    void assignTaskProcedure(
            @Param("P_TASK_ID") Long taskId,
            @Param("P_USER_ID") Long userId,
            @Param("P_TEAM_ID") Long teamId,
            @Param("P_PERIOD_START") LocalDate periodStart,
            @Param("P_PERIOD_END") LocalDate periodEnd,
            @Param("P_IS_MANDATORY") Character isMandatory,
            @Param("P_ASSIGNMENT_ID") Integer outputId
    );
}