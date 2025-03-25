package com.omergundogdu.bilgeadamecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.omergundogdu.bilgeadamecommercebackend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * E-ticaret sistemindeki bir siparişi temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, bir siparişin tüm bilgilerini içerir ve "orders" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Sipariş bilgileri arasında müşteri bilgileri (isim, e-posta, adres vb.), sipariş durumu,
 * ödeme kartı bilgileri, oluşturulma tarihi ve sipariş öğeleri yer almaktadır.
 * </p>
 *
 * <p>
 * Bu sınıf, Lombok anotasyonlarını kullanarak getter, setter, constructor ve builder gibi
 * gereksiz kod tekrarını azaltmaktadır.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    /**
     * Siparişin benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Siparişi veren kullanıcıya ait ID.
     * Bu bilgi gelen JWT'den alınmalı (ideal olarak SecurityContext'ten).
     */
    private Long userId;

    /**
     * Siparişi veren kişinin adı.
     */
    private String name;

    /**
     * Siparişi veren kişinin e-posta adresi.
     */
    private String email;

    /**
     * Siparişi veren kişinin adresi.
     */
    private String address;

    /**
     * Siparişi veren kişinin yaşadığı şehir.
     */
    private String city;

    /**
     * Siparişi veren kişinin posta kodu.
     */
    private String postalCode;

    /**
     * Siparişin durumu.
     * Varsayılan olarak {@link OrderStatus#PENDING} (Beklemede) olarak ayarlanır.
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    /**
     * Siparişin ödeme kartının son 4 hanesi.
     * Yalnızca kartın son dört hanesi tutulur.
     */
    private String cardLast4;

    /**
     * Siparişin oluşturulma tarihi.
     * Varsayılan olarak oluşturulma anı ile ayarlanır.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Siparişin içerdiği ürünler.
     * Bu ilişki {@link OrderItem} sınıfıyla 1'e çok (OneToMany) ilişki şeklinde tanımlanmıştır.
     * Orphan removal, bir sipariş öğesi silindiğinde veritabanından da silinmesini sağlar.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items;
}
