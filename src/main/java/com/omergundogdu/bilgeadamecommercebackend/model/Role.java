package com.omergundogdu.bilgeadamecommercebackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * E-ticaret sistemindeki bir rolü temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, kullanıcılara atanan rollerin bilgilerini içerir ve "roles" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Her rol, belirli bir isme ve izin setine (permissions) sahiptir. Rollerin kullanıcılara atanarak
 * sistemdeki erişim kontrolünü sağlamak için kullanılır.
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
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    /**
     * Rolün benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Rolün adı.
     * Bu alan boş olamaz ve benzersiz olmalıdır.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Rolün izinleri.
     * Bu koleksiyon, rolün sahip olduğu izinlerin listesini tutar.
     * İzinler, bir rolün ne tür erişimlere sahip olduğunu belirtir.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission")
    private Set<String> permissions;
}
