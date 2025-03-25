package com.omergundogdu.bilgeadamecommercebackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * E-ticaret sisteminde bir kategoriyi temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, ürünlerin gruplandığı kategorileri temsil eder ve "categories" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Kategoriler hiyerarşik bir yapıya sahip olabilir; yani bir ana kategoriye (parent) ait alt kategoriler (children) olabilir.
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
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    /**
     * Kategorinin benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kategorinin adı.
     * Bu alan boş olamaz ve benzersiz olmalıdır.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Kategorinin URL dostu ismi (slug).
     * Bu alan isteğe bağlıdır ve boş bırakılabilir.
     */
    private String slug;

    /**
     * Kategorinin görsel URL'si.
     * Bu alan isteğe bağlıdır ve boş bırakılabilir.
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Kategorinin üst kategorisi (parent).
     * Eğer bu kategori bir alt kategori ise üst kategorisine işaret eder.
     * Bu alan isteğe bağlıdır ve bir kategori ana kategori olabilir.
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * Kategorinin alt kategorileri (children).
     * Bu alan, bir kategoriye ait olan tüm alt kategorileri tutar.
     * CascadeType.ALL, alt kategorilerin de silinmesi gerektiğinde kullanılır.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();
}
