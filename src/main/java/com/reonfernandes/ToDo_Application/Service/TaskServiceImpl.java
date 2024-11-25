package com.reonfernandes.ToDo_Application.Service;

import com.reonfernandes.ToDo_Application.Model.Task;
import com.reonfernandes.ToDo_Application.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void createTask(String title, String task_description,
                           LocalDate date) {
        LOGGER.info("New Task Created..");
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(task_description);
        task.setDate(date);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        LOGGER.info("Task with id: "+ id + " deleted");
        taskRepository.deleteById(id);
    }

    @Override
    public void toggleTask(Long id) {
        LOGGER.info("Task with id: "+ id + " marked as completed..");
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, String title, String task_description, LocalDate date) {
        Task task = getTaskById(id);
        task.setTitle(title);
        task.setDescription(task_description);
        task.setDate(date);
        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found with id: " + id));
        LOGGER.debug("(Service) Retrieved Task: {}", task); // Log full Task object
        return task;
    }

}
