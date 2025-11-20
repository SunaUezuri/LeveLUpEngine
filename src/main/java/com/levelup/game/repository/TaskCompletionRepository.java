package com.levelup.game.repository;

import com.levelup.game.model.TaskCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCompletionRepository extends JpaRepository<TaskCompletion, Long> {

    List<TaskCompletion> findByUserIdOrderByCompletedAtDesc(Long userId);

    @Query("SELECT COALESCE(SUM(t.pointsValue), 0) " +
            "FROM TaskCompletion tc " +
            "JOIN tc.task t " +
            "WHERE tc.user.id = :userId")
    Integer getTotalPointsEarned(@Param("userId") Long userId);

    @Procedure(procedureName = "PKG_LEVELUP_APP.PR_CREATE_TASK_COMPLETION")
    void completeTaskProcedure(
            @Param("P_USER_ID") Long userId,
            @Param("P_TASK_ID") Long taskId,
            @Param("P_COMPLETION_ID") Integer outputId
    );
}