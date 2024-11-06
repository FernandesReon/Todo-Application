package com.reonfernandes.ToDo_Application.Service;

import com.reonfernandes.ToDo_Application.Model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    void createTask(String title);

    void deleteTask(Long id);

    void toggleTask(Long id);
}
