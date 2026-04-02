package com.janaprasath.scholarstack.controller;

import com.janaprasath.scholarstack.dto.LoginDTO;
import com.janaprasath.scholarstack.dto.UserRegistrationDTO;
import com.janaprasath.scholarstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController { // Fixed: Removed the <LoginRequest> tag

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationDTO registrationDto) {
        return userService.registerUser(registrationDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
        try {
            // FIX: Pass the whole 'request' object (1 argument) to match the Service
            String result = userService.loginUser(request);

            // Check if the service returned an error string
            if (result.startsWith("Error:")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }

            // Success: Return the JWT Token
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}