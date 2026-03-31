package com.giftapp.backend.service;

import com.giftapp.backend.dto.AuthRequest;
import com.giftapp.backend.dto.AuthResponse;

public interface AuthService {
    String register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}