package com.omergundogdu.bilgeadamecommercebackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * E-ticaret sistemindeki bir kullanıcıyı temsil eden entity sınıfı.
 * <p>
 * Bu sınıf, kullanıcı bilgilerini içerir ve "users" tablosuna karşılık gelir.
 * </p>
 *
 * <p>
 * Kullanıcı bilgileri arasında e-posta adresi, şifre, tam adı ve kullanıcının sahip olduğu roller bulunur.
 * Ayrıca kullanıcıya ait olan tüm izinler, rollerin izinlerinden türetilir.
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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * Kullanıcının benzersiz tanımlayıcısı.
     * Bu alan, veritabanı tarafından otomatik olarak üretilir.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kullanıcının e-posta adresi.
     * Bu alan boş olamaz ve benzersiz olmalıdır.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Kullanıcının şifresi.
     * Bu alan boş olamaz ve güvenlik amacıyla şifreli bir şekilde saklanır.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Kullanıcının tam adı.
     * Bu alan isteğe bağlıdır ve boş bırakılabilir.
     */
    private String fullName;

    /**
     * Kullanıcının rollerine ait bilgiler.
     * Bu ilişki, {@link Role} sınıfıyla çok'a çok (ManyToMany) ilişki şeklinde tanımlanmıştır.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    /**
     * Kullanıcıya ait tüm izinleri döner.
     * Bu metod, kullanıcının sahip olduğu tüm rollerin izinlerini toplar ve bir Set içinde döner.
     *
     * @return Kullanıcının tüm izinlerini içeren bir Set.
     */
    public Set<String> getAllPermissions() {
        Set<String> all = new HashSet<>();
        for (Role role : roles) {
            all.addAll(role.getPermissions());
        }
        return all;
    }
}