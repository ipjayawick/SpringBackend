package com.example.springbackend.controller;

import com.example.springbackend.model.Task;
import com.example.springbackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        // Use the service to update the task and check if it exists
        Task updatedTask = taskService.updateTask(id, task);

        // If task is null, it means the task was not found
        if (updatedTask == null) {
            return ResponseEntity.notFound().build(); // Return 404 if task not found
        }

        // If task is found and updated, return the updated task with 200 OK
        return ResponseEntity.ok(updatedTask);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<Task> filterTasks(@RequestParam(required = false) String status,
                                  @RequestParam(required = false) String assignee) {
        return taskService.filterTasks(status, assignee);
    }
}
