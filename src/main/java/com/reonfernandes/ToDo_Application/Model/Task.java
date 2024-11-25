package com.reonfernandes.ToDo_Application.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "todo_title", nullable = false)
    private String title;
    private String description;
    private LocalDate date;
    private boolean completed;

//    public Task(LocalDate date) {
//        this.date = date;
//    }
}
