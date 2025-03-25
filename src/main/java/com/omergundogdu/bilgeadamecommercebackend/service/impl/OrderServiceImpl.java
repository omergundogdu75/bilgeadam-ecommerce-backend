package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;
import com.omergundogdu.bilgeadamecommercebackend.model.Order;
import com.omergundogdu.bilgeadamecommercebackend.model.OrderItem;
import com.omergundogdu.bilgeadamecommercebackend.enums.OrderStatus;
import com.omergundogdu.bilgeadamecommercebackend.model.Product;
import com.omergundogdu.bilgeadamecommercebackend.repository.OrderRepository;
import com.omergundogdu.bilgeadamecommercebackend.repository.ProductRepository;
import com.omergundogdu.bilgeadamecommercebackend.service.read.OrderReadableService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.OrderWriteableService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sipariş ile ilgili işlemleri gerçekleştiren servis sınıfı.
 * <p>
 * Bu sınıf, sipariş oluşturma, siparişi iptal etme, siparişleri listeleme ve kullanıcıya ait siparişleri getirme işlemlerini gerçekleştirir.
 * Ayrıca, sipariş oluşturulurken ürün stoğu kontrolü yapılır ve sipariş öğeleri veritabanına kaydedilir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderReadableService, OrderWriteableService {

    /**
     * Sipariş veritabanı işlemleri için kullanılan repository.
     */
    private final OrderRepository orderRepository;

    /**
     * Ürün veritabanı işlemleri için kullanılan repository.
     */
    private final ProductRepository productRepository;

    /**
     * Yeni bir sipariş oluşturur.
     * <p>
     * Sipariş bilgileri (adres, ödeme bilgileri, sipariş öğeleri vb.) alınarak bir sipariş nesnesi oluşturulur.
     * Sipariş oluşturulmadan önce her bir ürünün stok durumu kontrol edilir ve stok miktarı güncellenir.
     * </p>
     *
     * @param req Sipariş oluşturma isteği.
     * @param userId Siparişi veren kullanıcı ID'si.
     */
    @Override
    @Transactional
    public void createOrder(OrderRequest req, Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setName(req.getShipping().getName());
        order.setEmail(req.getShipping().getEmail());
        order.setAddress(req.getShipping().getAddress());
        order.setCity(req.getShipping().getCity());
        order.setPostalCode(req.getShipping().getPostalCode());
        order.setStatus(OrderStatus.PENDING);

        String cardNumber = req.getPayment().getCardNumber();
        order.setCardLast4(cardNumber.substring(cardNumber.length() - 4));

        List<OrderItem> orderItems = req.getItems().stream().map(dto -> {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Ürün bulunamadı: " + dto.getProductId()));

            if (product.getStock() < dto.getQuantity()) {
                throw new RuntimeException(product.getName() + " ürününde yeterli stok yok.");
            }

            product.setStock(product.getStock() - dto.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setProductId(dto.getProductId());
            item.setName(dto.getName());
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());
            item.setOrder(order);

            return item;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        orderRepository.save(order);
    }

    /**
     * Verilen ID'ye sahip siparişi iptal eder.
     * <p>
     * Siparişin durumu "CANCELLED" olarak güncellenir ve veritabanına kaydedilir.
     * </p>
     *
     * @param id İptal edilecek siparişin ID'si.
     */
    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
        order.setStatus(OrderStatus.CANCELLED); // Enum veya String
        orderRepository.save(order);
    }

    /**
     * Verilen ID'ye sahip siparişi getirir.
     * <p>
     * Eğer sipariş bulunamazsa, hata fırlatılır.
     * </p>
     *
     * @param id Siparişin benzersiz ID'si.
     * @return İlgili siparişi içeren {@link Order} nesnesi.
     */
    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
    }

    /**
     * Verilen kullanıcı ID'sine ait tüm siparişleri getirir.
     * <p>
     * Kullanıcının tüm siparişlerini listeleyerek döndürür.
     * </p>
     *
     * @param userId Kullanıcının benzersiz ID'si.
     * @return Kullanıcıya ait tüm siparişleri içeren liste.
     */
    @Override
    public List<Order> getAllByUserId(Long userId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getUserId() != null && order.getUserId().equals(userId))
                .toList();
    }

    /**
     * Tüm siparişleri getirir.
     * <p>
     * Veritabanındaki tüm siparişleri döndürür.
     * </p>
     *
     * @return Tüm siparişleri içeren liste.
     */
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
