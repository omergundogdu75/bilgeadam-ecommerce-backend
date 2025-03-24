package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.model.Order;

import java.util.List;

public interface OrderReadableService {
    Order getById(Long id);
    List<Order> getAllByUserId(Long userId);
    List<Order> getAll(); // opsiyonel: admin için
}
