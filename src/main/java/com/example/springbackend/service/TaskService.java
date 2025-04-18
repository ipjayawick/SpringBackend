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

    private final EmailService emailService;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        if (savedTask.getAssignee() != null && !savedTask.getAssignee().isEmpty()) {
            emailService.sendEmail(
                    savedTask.getAssignee(),
                    "Task assigned to you!",
                    "Title: " + savedTask.getTitle() + "\nDescription: " + savedTask.getDescription()
            );
        }

        return Optional.of(savedTask);
    }

    public Optional<Task> updateTask(String id, Task updatedTask) {
        return taskRepository.findById(id).map(existingTask -> {
            boolean assigneeChanged = !Objects.equals(existingTask.getAssignee(), updatedTask.getAssignee());

            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setAssignee(updatedTask.getAssignee());
            existingTask.setUpdatedAt(LocalDateTime.now());

            Task saved = taskRepository.save(existingTask);

            if (assigneeChanged && saved.getAssignee() != null) {
                emailService.sendEmail(
                        saved.getAssignee(),
                        "Task assigned to you!",
                        "Title: " + saved.getTitle() + "\nDescription: " + saved.getDescription()
                );
            }

            return saved;
        });
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
