package com.reonfernandes.ToDo_Application.Service.impl;

import com.reonfernandes.ToDo_Application.Exception.ResourceNotFound;
import com.reonfernandes.ToDo_Application.Model.Priority;
import com.reonfernandes.ToDo_Application.Model.Task;
import com.reonfernandes.ToDo_Application.Repository.TaskRepository;
import com.reonfernandes.ToDo_Application.Service.TaskServices;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServicesImpl implements TaskServices {
    private final TaskRepository taskRepository;

    public TaskServicesImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(Task task) {
        // Set priority to default if null
        if (task.getPriority() == null){
            task.setPriority(Priority.MEDIUM);
        }
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void updateTask(Task task, Long id) {
        Task updatedTask = taskRepository.findById(task.getId()).orElseThrow(
                ()-> new ResourceNotFound("Task with id: " + task.getId() + " not found.")
        );
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setDate(task.getDate());
        updatedTask.setTime(task.getTime());
        updatedTask.setPriority(task.getPriority());
        taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void toggleTask(Long id) {
        Task toggleTask = taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Invalid task id."));
        toggleTask.setCompleted(!toggleTask.isCompleted());
        taskRepository.save(toggleTask);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Task not found with id: " + id));
    }
}
