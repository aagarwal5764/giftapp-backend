package com.giftapp.backend.controller;

import com.giftapp.backend.entity.User;
import com.giftapp.backend.repository.UserRepository;
import com.giftapp.backend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER API
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    // LOGIN API (WITH JWT)
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .map(u -> {
                    if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                        return jwtUtil.generateToken(u.getUsername(), u.getRole());
                    } else {
                        return "Invalid password";
                    }
                })
                .orElse("User not found");
    }
}