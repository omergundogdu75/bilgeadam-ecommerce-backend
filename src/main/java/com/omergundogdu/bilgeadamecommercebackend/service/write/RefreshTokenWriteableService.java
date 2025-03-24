package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.model.RefreshToken;
import com.omergundogdu.bilgeadamecommercebackend.model.User;

public interface RefreshTokenWriteableService {
    RefreshToken createRefreshToken(User user);
    void deleteByUser(User user);
    RefreshToken verifyExpiration(RefreshToken token);
}