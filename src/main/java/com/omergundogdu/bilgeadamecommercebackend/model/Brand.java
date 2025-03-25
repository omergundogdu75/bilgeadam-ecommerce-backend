package com.omergundogdu.bilgeadamecommercebackend.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * E-ticaret sisteminde bir markayı temsil eden entity sınıfı.
 * <p>
 * Bir marka, belirli bir ürün setiyle ilişkilendirilen bir şirket veya ürün adını ifade eder.
 * Bu sınıf, veritabanındaki "brands" tablosuna karşılık gelmektedir.
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
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    /**
     * Markanın benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Markanın adı.
     * Bu alan boş olamaz ve benzersiz olmalıdır.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Markanın açıklaması.
     * Bu alan isteğe bağlıdır ve boş bırakılabilir.
     */
    private String description;
}
