package com.example.GestionTareas.Controller;

import com.example.GestionTareas.Model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/tasks")

public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Task>>getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<List<Task>> createTasks(@RequestBody List<Task> tasks) {
        this.tasks.addAll(tasks);
        return new ResponseEntity<>(tasks, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        for (Task task: tasks) {
            if (task.getId().equals(id)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
