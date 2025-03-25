package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;
import com.omergundogdu.bilgeadamecommercebackend.model.Order;

import com.omergundogdu.bilgeadamecommercebackend.security.JwtFilter;
import com.omergundogdu.bilgeadamecommercebackend.security.JwtUtil;
import com.omergundogdu.bilgeadamecommercebackend.service.read.OrderReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.OrderWriteableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderWriteableService orderWriteableService;
    private final OrderReadableService orderReadableService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token); // ✅ burada alınıyor
        orderWriteableService.createOrder(req, userId);
        return ResponseEntity.ok("Sipariş başarıyla oluşturuldu");
    }


    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderReadableService.getAllByUserId(userId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderReadableService.getAll();
    }
}
