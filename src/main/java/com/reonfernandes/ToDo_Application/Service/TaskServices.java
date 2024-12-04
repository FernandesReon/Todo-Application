package com.reonfernandes.ToDo_Application.Service;

import com.reonfernandes.ToDo_Application.Model.Priority;
import com.reonfernandes.ToDo_Application.Model.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskServices {
    void createTask(Task task);
    List<Task> getAllTasks();
    void updateTask(Task task, Long id);
    void deleteTask(Long id);
    void toggleTask(Long id);
    Task getTaskById(Long id);
}
