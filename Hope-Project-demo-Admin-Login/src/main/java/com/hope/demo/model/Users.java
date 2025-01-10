package com.hope.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Document
public class Users {
    @Id
    private String id;

    @NotBlank(message = "Username is required")
    @Indexed(unique = true) 
    @Pattern(regexp = "^(?!_)(?!.*__)[A-Za-z0-9_]{3,30}(?<!_)$", message = "Username should only contain letters, numbers, and underscores, and must be between 3 and 30 characters. No leading, trailing, or consecutive underscores are allowed.")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a number, and a special character")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[6789][0-9]{9}$", message = "Contact number must be a valid 10-digit Indian number starting with 6, 7, 8, or 9")
    @Indexed(unique = true) 
    private String contactNo;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(ADMIN|DOCTOR|USER)$", message = "Role must be ADMIN, DOCTOR, or USER")
    private String role;
}
