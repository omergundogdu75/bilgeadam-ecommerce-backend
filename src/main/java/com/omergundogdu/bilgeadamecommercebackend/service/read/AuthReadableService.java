package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.TokenRefreshRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.TokenRefreshResponse;

public interface AuthReadableService {
    TokenRefreshResponse refreshToken(TokenRefreshRequest request);
}
