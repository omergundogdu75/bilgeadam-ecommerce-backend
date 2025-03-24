package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;
import com.omergundogdu.bilgeadamecommercebackend.model.Order;

import com.omergundogdu.bilgeadamecommercebackend.service.read.OrderReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.OrderWriteableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderWriteableService orderWriteableService;
    private final OrderReadableService orderReadableService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest req) {
        orderWriteableService.createOrder(req);
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
