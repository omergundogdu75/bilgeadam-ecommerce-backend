package com.omergundogdu.bilgeadamecommercebackend.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * E-ticaret sistemindeki bir ürünü temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, ürünlerin tüm bilgilerini içerir ve "products" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Ürün bilgileri arasında ürün adı, açıklaması, fiyatı, stoğu, görsel URL'si ve hangi kategori ve markaya ait olduğu bilgileri yer alır.
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
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    /**
     * Ürünün benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ürünün adı.
     * Bu alan boş olamaz.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Ürünün açıklaması.
     * Bu alan isteğe bağlıdır ve boş bırakılabilir.
     */
    private String description;

    /**
     * Ürünün fiyatı.
     * Bu alan, ürünün satış fiyatını tutar.
     */
    private Double price;

    /**
     * Ürünün stoğu.
     * Bu alan, mevcut ürün sayısını belirtir.
     */
    private Integer stock;

    /**
     * Ürünün görsel URL'si.
     * Bu alan isteğe bağlıdır ve boş bırakılabilir.
     */
    private String imageUrl;

    /**
     * Ürünün ait olduğu kategori.
     * Bu ilişki {@link Category} sınıfıyla çok'a bir (ManyToOne) ilişki şeklinde tanımlanmıştır.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Ürünün ait olduğu marka.
     * Bu ilişki {@link Brand} sınıfıyla çok'a bir (ManyToOne) ilişki şeklinde tanımlanmıştır.
     */
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
