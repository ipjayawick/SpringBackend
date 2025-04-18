package com.example.springbackend.service;

import com.example.springbackend.model.Task;
import com.example.springbackend.repository.TaskRepository;
import com.example.springbackend.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private EmailService emailService;
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        // Send email if the assignee is provided
        if (savedTask.getAssignee() != null && !savedTask.getAssignee().isEmpty()) {
//            emailService.sendAssigneeNotification(savedTask.getAssignee(), savedTask.getTitle(), true);
        }

        return savedTask;
    }

    public Task updateTask(String id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElse(null);

        if (existingTask == null) {
            return null; // Task not found, return null instead of throwing an exception
        }

        // Check if the assignee has changed
        boolean assigneeChanged = !Objects.equals(existingTask.getAssignee(), updatedTask.getAssignee());

        // Update the fields of the task
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setAssignee(updatedTask.getAssignee());
        existingTask.setUpdatedAt(LocalDateTime.now());

        // Save the updated task
        Task saved = taskRepository.save(existingTask);

        // Send email if the assignee changed
        if (assigneeChanged && saved.getAssignee() != null) {
            emailService.sendSimpleEmail(saved.getAssignee(), "Task assigned to you!", "Title: " + saved.getTitle() + "\n" + "Description: " + saved.getDescription());
//            emailService.sendAssigneeNotification(saved.getAssignee(), saved.getTitle(), false);
        }

        return saved;

//        return taskRepository.findById(id).map(task -> {
//            boolean assigneeChanged = !Objects.equals(task.getAssignee(), updatedTask.getAssignee());
//            task.setTitle(updatedTask.getTitle());
//            task.setDescription(updatedTask.getDescription());
//            task.setStatus(updatedTask.getStatus());
//            task.setAssignee(updatedTask.getAssignee());
//            task.setUpdatedAt(LocalDateTime.now());
//            return taskRepository.save(task);
//        });
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> filterTasks(String status, String assignee) {
        if (status != null && assignee != null) {
            return taskRepository.findByStatusAndAssignee(status, assignee);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        } else if (assignee != null) {
            return taskRepository.findByAssignee(assignee);
        } else {
            return taskRepository.findAll();
        }
    }
}
