package com.example.taskmanager.dto;

import com.example.taskmanager.models.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
