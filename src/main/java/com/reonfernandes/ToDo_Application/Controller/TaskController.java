package com.reonfernandes.ToDo_Application.Controller;

import com.reonfernandes.ToDo_Application.Model.Task;
import com.reonfernandes.ToDo_Application.Service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        LOGGER.info("(Controller) Fetching all tasks...");
        List<Task> tasks = taskService.getAllTasks();
        LOGGER.debug("(Controller) Number of tasks fetched: {}", tasks.size());
        model.addAttribute("taskList", tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title, @RequestParam String task_description,
                             @RequestParam(required = false) LocalDate date) {
        LOGGER.info("(Controller) Creating a new task with title: {}", title);
        taskService.createTask(title, task_description, date);
        LOGGER.debug("(Controller) Task '{}' created successfully.", title);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        LOGGER.info("(Controller) Deleting task with ID: {}", id);
        taskService.deleteTask(id);
        LOGGER.debug("(Controller) Task with ID {} deleted successfully.", id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        LOGGER.info("(Controller) Toggling completion status for task with ID: {}", id);
        taskService.toggleTask(id);
        LOGGER.debug("(Controller) Task with ID {} toggled successfully.", id);
        return "redirect:/tasks";
    }

    @GetMapping("/updateTaskPage/{id}")
    public String updateTask(@PathVariable Long id, Model model) {
        LOGGER.info("(Controller) Fetching task with ID: {} for update", id);
        Task task = taskService.getTaskById(id);
        if (task != null) {
            LOGGER.debug("(Controller) Task with ID {} found: {}", id, task);
            model.addAttribute("task", task);
            return "updateTask";
        } else {
            LOGGER.warn("(Controller) Task with ID {} not found.", id);
            return "redirect:/tasks";
        }
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        LOGGER.info("(Controller) Updating task with ID: {}", task.getId());
        taskService.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getDate());
        LOGGER.debug("(Controller) Task with ID {} updated successfully.", task.getId());
        return "redirect:/tasks";
    }
}
