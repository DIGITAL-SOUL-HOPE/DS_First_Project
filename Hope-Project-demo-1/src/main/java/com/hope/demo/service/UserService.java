package com.hope.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hope.demo.dto.LoginRequest;
import com.hope.demo.exception.InvalidCredentialsException;
import com.hope.demo.exception.UserNotFoundException;
import com.hope.demo.model.Users;
import com.hope.demo.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService; // JWT service to generate tokens

    @Autowired
    private UserRepo repo; // Repository for interacting with the database

    // BCryptPasswordEncoder with a strength of 12 (default strength is 10)
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Register method for creating a new user
    public Users register(Users user) {
        // Check if the username already exists in the database
        if (repo.existsByUsername(user.getUsername())) {
            throw new DuplicateKeyException("Username already exists.");
        }
        // Check if the contact number already exists in the database
        if (repo.existsByContactNo(user.getContactNo())) {
            throw new DuplicateKeyException("Contact number already exists.");
        }
        // Encrypt the password before saving it
        user.setPassword(encoder.encode(user.getPassword()));
        // Save the user in the database
        repo.save(user);
        return user;
    }

    // Verify login credentials
    public String verifyLogin(LoginRequest loginRequest) {
        // Fetch the user from the database by username
        Optional<Users> optionalUser = Optional.ofNullable(repo.findByUsername(loginRequest.getUsername()));
        
        // If user is not found, throw UserNotFoundException
        Users existingUser = optionalUser.orElseThrow(() -> {
            System.out.println("User not found: " + loginRequest.getUsername());
            return new UserNotFoundException("User not found");
        });

        // Trim the raw password entered to avoid issues with leading/trailing spaces
        String rawPassword = loginRequest.getPassword().trim();

        // Log password lengths for debugging purposes (do not log passwords themselves)
        System.out.println("Encoded Password Length in DB: " + existingUser.getPassword().length());
        System.out.println("Raw Password Length: " + rawPassword.length());

        // Verify if the entered raw password matches the stored hashed password using BCryptPasswordEncoder
        if (encoder.matches(rawPassword, existingUser.getPassword())) {
            // Password matched, generating and returning JWT token
            System.out.println("Password matched. Generating token...");
            return jwtService.generateToken(loginRequest.getUsername());
        } else {
            // If the password doesn't match, throw InvalidCredentialsException
            System.out.println("Password mismatch for user: " + loginRequest.getUsername());
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }
}
