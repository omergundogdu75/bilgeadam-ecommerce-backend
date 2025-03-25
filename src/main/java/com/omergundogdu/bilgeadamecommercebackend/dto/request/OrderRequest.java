package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.Data;
import java.util.List;

/**
 * Sipariş oluşturmak için kullanılan veri transfer nesnesidir (DTO).
 *
 * <p>Bu sınıf; gönderim bilgileri, ödeme bilgileri ve sipariş edilen ürünlerin listesini içerir.</p>
 *
 * <p><b>Kullanım:</b> /api/orders endpoint'ine POST isteği yapılırken kullanılır.</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
public class OrderRequest {

    /**
     * Kargo ve teslimat bilgilerini içeren alt sınıf.
     */
    private ShippingInfo shipping;

    /**
     * Kredi kartı bilgilerini içeren alt sınıf.
     */
    private PaymentInfo payment;

    /**
     * Sipariş edilen ürünlerin listesi.
     */
    private List<OrderItemDto> items;

    /**
     * Siparişe ait teslimat/kargo bilgilerini taşıyan sınıf.
     */
    @Data
    public static class ShippingInfo {

        /**
         * Alıcının adı ve soyadı.
         */
        private String name;

        /**
         * Alıcının e-posta adresi.
         */
        private String email;

        /**
         * Teslimat adresi.
         */
        private String address;

        /**
         * Şehir bilgisi.
         */
        private String city;

        /**
         * Posta kodu.
         */
        private String postalCode;
    }

    /**
     * Siparişe ait ödeme bilgilerini taşıyan sınıf.
     */
    @Data
    public static class PaymentInfo {

        /**
         * Kart numarası (frontend'de maskelenmelidir).
         */
        private String cardNumber;

        /**
         * Kartın son kullanma ayı.
         */
        private String cardMonth;

        /**
         * Kartın son kullanma yılı.
         */
        private String cardYear;

        /**
         * Kartın güvenlik kodu (CVV).
         */
        private String cardCvv;
    }

    /**
     * Sipariş edilen ürünleri temsil eden sınıf.
     */
    @Data
    public static class OrderItemDto {

        /**
         * Ürünün benzersiz ID’si.
         */
        private Long productId;

        /**
         * Ürün adı.
         */
        private String name;

        /**
         * Sipariş edilen ürün adedi.
         */
        private int quantity;

        /**
         * Ürünün birim fiyatı.
         */
        private double price;
    }
}
