package com.giftapp.backend.controller;

import com.giftapp.backend.entity.User;
import com.giftapp.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully";
    }

    // Login (basic for now)
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .map(u -> u.getPassword().equals(user.getPassword()) ?
                        "Login successful" : "Invalid password")
                .orElse("User not found");
    }
}