package com.example.springbackend.repository;

import com.example.springbackend.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByStatusAndAssignee(String status, String assignee);
    List<Task> findByStatus(String status);
    List<Task> findByAssignee(String assignee);
}