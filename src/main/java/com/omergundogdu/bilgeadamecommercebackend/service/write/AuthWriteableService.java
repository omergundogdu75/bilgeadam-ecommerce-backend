package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.LoginRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.RegisterRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.AuthResponse;

public interface AuthWriteableService {
    AuthResponse register(RegisterRequest request);
    AuthResponse adminRegister(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
