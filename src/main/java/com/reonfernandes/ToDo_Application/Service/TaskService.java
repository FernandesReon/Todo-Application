package com.reonfernandes.ToDo_Application.Service;

import com.reonfernandes.ToDo_Application.Model.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();

    void createTask(String title, String task_description, LocalDate date, LocalTime time);

    void deleteTask(Long id);

    void toggleTask(Long id);
    

    void updateTask(Long id, String title, String task_description, LocalDate date, LocalTime time);

    Task getTaskById(Long id);
}
