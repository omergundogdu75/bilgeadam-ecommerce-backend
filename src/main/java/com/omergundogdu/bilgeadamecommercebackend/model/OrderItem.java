package com.omergundogdu.bilgeadamecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * E-ticaret sistemindeki bir sipariş öğesini (ürün) temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, bir siparişte bulunan her bir ürünün detaylarını içerir ve "order_item" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Sipariş öğesi, ürün bilgileri (id, isim, miktar, fiyat) ve hangi siparişe ait olduğu bilgisini içerir.
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
@Table(name = "order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    /**
     * Sipariş öğesinin benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Sipariş öğesinin ait olduğu ürünün benzersiz kimliği.
     */
    private Long productId;

    /**
     * Sipariş öğesinin ürün adı.
     */
    private String name;

    /**
     * Sipariş öğesinin miktarı.
     * Bu alan, sipariş edilen ürünün sayısını belirtir.
     */
    private int quantity;

    /**
     * Sipariş öğesinin birim fiyatı.
     * Bu alan, ürünün birim fiyatını tutar.
     */
    private double price;

    /**
     * Sipariş öğesinin ait olduğu sipariş.
     * Bu ilişki {@link Order} sınıfıyla çok'a bir (ManyToOne) ilişki şeklinde tanımlanmıştır.
     * {@link JsonBackReference} anotasyonu, JSON dönerken bu alanın serileştirilmesini engeller.
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
}
