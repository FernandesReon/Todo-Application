package com.reonfernandes.ToDo_Application.Controller;

import com.reonfernandes.ToDo_Application.Model.Task;
import com.reonfernandes.ToDo_Application.Service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getAllTasks(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("taskList", tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title){
        LOGGER.info("(Controller) Create Task..");
        taskService.createTask(title);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        LOGGER.info("(Controller) Delete Task");
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        LOGGER.info("(Controller) Toggle Task (Marked as completed..)");
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }
}
