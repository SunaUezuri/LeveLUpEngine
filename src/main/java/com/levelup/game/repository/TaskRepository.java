package com.levelup.game.repository;

import com.levelup.game.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByIsActive(Character isActive);

    @Procedure(procedureName = "PKG_LEVELUP_APP.PR_CREATE_TASK")
    void createTaskProcedure(
            @Param("P_TITLE") String title,
            @Param("P_DESCRIPTION") String description,
            @Param("P_POINTS_VALUE") Integer pointsValue,
            @Param("P_TASK_TYPE") String taskType,
            @Param("P_TASK_ID") Integer outputId
    );
}