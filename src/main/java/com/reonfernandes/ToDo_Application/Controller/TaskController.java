package com.reonfernandes.ToDo_Application.Controller;

import com.reonfernandes.ToDo_Application.Model.Priority;
import com.reonfernandes.ToDo_Application.Model.Task;
import com.reonfernandes.ToDo_Application.Service.impl.TaskServicesImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServicesImpl taskServices;

    public TaskController(TaskServicesImpl taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        List<Task> allTask = taskServices.getAllTasks();
        model.addAttribute("allTask", allTask);
        return "tasks";
    }

    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/createTask")
    public String createTask(Model model){
        Task task = new Task();
        task.setPriority(Priority.MEDIUM);
        model.addAttribute("task", task);
        return "createTask";
    }

    @PostMapping("/process-form")
    public String processingForm(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            return "createTask";
        }

        if (task.getPriority() == null) {
            task.setPriority(Priority.MEDIUM);
        }

        taskServices.createTask(task);
        return "redirect:/tasks/home";
    }

    @GetMapping("/toggleTask/{id}")
    public String toggleTask(@PathVariable Long id){
        taskServices.toggleTask(id);
        return "redirect:/tasks/home";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id){
        taskServices.deleteTask(id);
        return "redirect:/tasks/home";
    }

    @GetMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id, Model model){
        Task task = taskServices.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }

    @PostMapping("/updateTaskForm/{id}")
    public String updateTaskForm(@Valid @ModelAttribute("task") Task task,
                                 BindingResult result){
        if (result.hasErrors()){
            return "updateTask";
        }
        taskServices.updateTask(task, task.getId());
        return "redirect:/tasks/home";
    }

}
