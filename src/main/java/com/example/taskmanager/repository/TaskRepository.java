package com.example.taskmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.models.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwner(User owner);
}
