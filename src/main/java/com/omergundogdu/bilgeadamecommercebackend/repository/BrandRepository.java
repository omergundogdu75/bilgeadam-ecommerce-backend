package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Marka veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link Brand} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, marka adı ile ilgili bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {

    /**
     * Belirtilen isimde bir markanın veritabanında olup olmadığını kontrol eder.
     * <p>
     * Bu metod, markaların adlarını büyük/küçük harf duyarsız bir şekilde kontrol eder.
     * </p>
     *
     * @param name Markanın adı.
     * @return Eğer belirtilen isimde bir marka varsa true, yoksa false döner.
     */
    boolean existsByNameIgnoreCase(String name);
}
