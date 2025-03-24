package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private ShippingInfo shipping;
    private PaymentInfo payment;
    private List<OrderItemDto> items;

    @Data
    public static class ShippingInfo {
        private String name;
        private String email;
        private String address;
        private String city;
        private String postalCode;
    }

    @Data
    public static class PaymentInfo {
        private String cardNumber;
        private String cardMonth;
        private String cardYear;
        private String cardCvv;
    }

    @Data
    public static class OrderItemDto {
        private Long productId;
        private String name;
        private int quantity;
        private double price;
    }
}
