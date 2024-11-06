package com.reonfernandes.ToDo_Application.Repository;

import com.reonfernandes.ToDo_Application.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
