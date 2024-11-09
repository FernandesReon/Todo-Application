package com.reonfernandes.ToDo_Application.Service;

import com.reonfernandes.ToDo_Application.Model.Task;
import com.reonfernandes.ToDo_Application.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void createTask(String title, String task_description) {
        LOGGER.info("New Task Created..");
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(task_description);
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
}
