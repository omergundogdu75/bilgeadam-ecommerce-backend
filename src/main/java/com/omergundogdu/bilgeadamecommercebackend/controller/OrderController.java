package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;
import com.omergundogdu.bilgeadamecommercebackend.model.Order;
import com.omergundogdu.bilgeadamecommercebackend.security.JwtUtil;
import com.omergundogdu.bilgeadamecommercebackend.service.read.OrderReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.OrderWriteableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Siparişlerle (Order) ilgili işlemleri yöneten REST controller sınıfıdır.
 * <p>
 * Sipariş oluşturma, kullanıcının siparişlerini listeleme, tüm siparişleri getirme ve sipariş iptali gibi işlemleri içerir.
 * JWT ile kimlik doğrulama yapılır.
 * </p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Order API", description = "Sipariş işlemleri ile ilgili API")
public class OrderController {

    private final OrderWriteableService orderWriteableService;
    private final OrderReadableService orderReadableService;
    private final JwtUtil jwtUtil;

    /**
     * Yeni bir sipariş oluşturur.
     *
     * @param req        Sipariş bilgilerini içeren DTO
     * @param authHeader Authorization başlığından gelen JWT token
     * @return Sipariş oluşturma sonucu mesajı
     */
    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);
        orderWriteableService.createOrder(req, userId);
        return ResponseEntity.ok("Sipariş başarıyla oluşturuldu");
    }

    /**
     * Belirli bir kullanıcıya ait tüm siparişleri getirir (Admin tarafında kullanılabilir).
     *
     * @param userId Kullanıcının ID'si
     * @return Kullanıcının siparişleri
     */
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderReadableService.getAllByUserId(userId);
    }

    /**
     * Sistemdeki tüm siparişleri listeler (Admin yetkisi gerektirir).
     *
     * @return Tüm sipariş listesi
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderReadableService.getAll();
    }

    /**
     * Giriş yapmış kullanıcıya ait siparişleri listeler.
     *
     * @param authHeader Authorization başlığından gelen JWT token
     * @return Kullanıcının kendi siparişleri
     */
    @GetMapping("/my")
    public ResponseEntity<List<Order>> getMyOrders(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);
        List<Order> orders = orderReadableService.getAllByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Belirtilen ID'ye sahip siparişi iptal eder.
     *
     * @param id Sipariş ID'si
     * @return İptal işlemi sonucu mesajı
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        orderWriteableService.cancelOrder(id);
        return ResponseEntity.ok("Sipariş iptal edildi");
    }
}
