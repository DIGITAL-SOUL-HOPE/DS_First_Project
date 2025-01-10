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
    private JWTService jwtService;

    @Autowired
    private UserRepo repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /**
     * Registers a new user with validation for unique username and contact number.
     * Default role is set to USER if not provided.
     */
    public Users register(Users user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new DuplicateKeyException("Username '" + user.getUsername() + "' already exists.");
        }

        if (repo.existsByContactNo(user.getContactNo())) {
            throw new DuplicateKeyException("Contact number '" + user.getContactNo() + "' already exists.");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        } else {
            String role = user.getRole().toUpperCase();
            if (!role.equals("USER") && !role.equals("ADMIN") && !role.equals("DOCTOR")) {
                throw new IllegalArgumentException("Invalid role provided: " + role);
            }
            user.setRole(role);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    /**
     * Verifies login for any role (USER, ADMIN, DOCTOR).
     */
    public String verifyLogin(LoginRequest loginRequest) {
        return verifyRoleLogin(loginRequest, loginRequest.getRole());
    }

    /**
     * Validates login credentials and role for the given LoginRequest and role.
     * This method verifies that the user exists, the role matches, and the password is correct.
     */
    private String verifyRoleLogin(LoginRequest loginRequest, String role) {
        // Find user by username
        Users user = Optional.ofNullable(repo.findByUsername(loginRequest.getUsername()))
                .orElseThrow(() -> new UserNotFoundException("Username '" + loginRequest.getUsername() + "' not found"));

        // Check if the role matches
        if (!user.getRole().equalsIgnoreCase(role)) {
            throw new InvalidCredentialsException(
                    "Role mismatch: Expected '" + role + "' but found '" + user.getRole() + "'");
        }

        // Verify password
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException(
                    "Password mismatch for username '" + loginRequest.getUsername() + "'");
        }

        // Generate JWT token and return it
        return jwtService.generateToken(user.getUsername(), user.getRole());
    }
}
