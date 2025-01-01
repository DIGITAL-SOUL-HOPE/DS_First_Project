package com.hope.demo.controller;

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


    @PostMapping("/register")
    public Users register(@Valid @RequestBody Users user) {
        return service.register(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = service.verifyLogin(loginRequest);
        return ResponseEntity.ok(token);
    }
   
}
