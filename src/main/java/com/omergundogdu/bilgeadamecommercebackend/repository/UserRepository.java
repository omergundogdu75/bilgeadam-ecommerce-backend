package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Kullanıcı veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link User} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, kullanıcılar ile ilgili bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Verilen e-posta adresine sahip bir kullanıcıyı döner.
     * <p>
     * Bu metod, belirli bir e-posta adresine sahip kullanıcıyı bulmak için kullanılır.
     * </p>
     *
     * @param email Kullanıcının e-posta adresi.
     * @return Verilen e-posta ile eşleşen kullanıcı, bulunamazsa boş döner.
     */
    Optional<User> findByEmail(String email);

    /**
     * Verilen e-posta adresine sahip bir kullanıcının veritabanında olup olmadığını kontrol eder.
     * <p>
     * Bu metod, belirli bir e-posta adresinin veritabanında kayıtlı olup olmadığını sorgulamak için kullanılır.
     * </p>
     *
     * @param email Kullanıcının e-posta adresi.
     * @return Eğer belirtilen e-posta adresine sahip bir kullanıcı varsa true, yoksa false döner.
     */
    boolean existsByEmail(String email);
}
