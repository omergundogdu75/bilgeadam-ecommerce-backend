package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Rol veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link Role} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, rol ile ilgili bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Verilen isimde bir rolü döner.
     * <p>
     * Bu metod, belirli bir rolü adıyla sorgulamak için kullanılır.
     * </p>
     *
     * @param name Rolün adı.
     * @return Verilen isimle eşleşen rol, bulunamazsa boş döner.
     */
    Optional<Role> findByName(String name);
}
