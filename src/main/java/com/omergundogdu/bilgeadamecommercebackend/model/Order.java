package com.omergundogdu.bilgeadamecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // gelen JWT'den alınmalı (ideal: SecurityContext'ten)

    private String name;
    private String email;
    private String address;
    private String city;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;


    private String cardLast4; // sadece son 4 hanesi tutulur
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items;
}

