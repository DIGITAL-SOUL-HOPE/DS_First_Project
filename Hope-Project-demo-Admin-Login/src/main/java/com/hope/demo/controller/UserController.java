package com.hope.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hope.demo.dto.LoginRequest;
import com.hope.demo.model.Users;
import com.hope.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    // Register new user with default role USER
    @PostMapping("/register")
    public ResponseEntity<Users> register(@Valid @RequestBody Users user) {
        user.setRole("USER"); // Default to USER
        Users registeredUser = service.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Register admin user with role ADMIN
    @PostMapping("/admin/register")
    public ResponseEntity<Users> adminRegister(@Valid @RequestBody Users user) {
        user.setRole("ADMIN");
        Users registeredAdmin = service.register(user);
        return ResponseEntity.ok(registeredAdmin);
    }

    // Register doctor user with role DOCTOR
    @PostMapping("/doctor/register")
    public ResponseEntity<Users> doctorRegister(@Valid @RequestBody Users user) {
        user.setRole("DOCTOR");
        Users registeredDoctor = service.register(user);
        return ResponseEntity.ok(registeredDoctor);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        if (loginRequest.getRole() == null || loginRequest.getRole().isEmpty()) {
            loginRequest.setRole("USER"); // Set a default role if not provided
        }
        String token = service.verifyLogin(loginRequest);
        return ResponseEntity.ok(Map.of("token", token, "role", loginRequest.getRole()));
    }
}
