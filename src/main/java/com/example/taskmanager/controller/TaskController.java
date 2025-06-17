package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.models.User;
import com.example.taskmanager.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> list(@AuthenticationPrincipal User user) {
        return taskRepository.findByOwner(user);
    }

    @PostMapping
    public Task create(@AuthenticationPrincipal User user, @RequestBody Task task) {
        task.setOwner(user);
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task taskData, @AuthenticationPrincipal User user) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        taskRepository.delete(task);
        return "Task deleted";
    }
}
