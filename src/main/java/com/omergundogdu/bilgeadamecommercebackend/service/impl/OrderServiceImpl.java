package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;
import com.omergundogdu.bilgeadamecommercebackend.model.Order;
import com.omergundogdu.bilgeadamecommercebackend.model.OrderItem;
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

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderReadableService, OrderWriteableService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

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

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}


