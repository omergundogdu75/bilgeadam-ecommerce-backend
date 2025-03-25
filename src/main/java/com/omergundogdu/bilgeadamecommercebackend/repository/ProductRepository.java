package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Ürün veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link Product} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, ürünlere ait bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Verilen kategori ID'sine ait tüm ürünleri döner.
     * <p>
     * Bu metod, bir kategoriye ait tüm ürünleri listelemek için kullanılır.
     * </p>
     *
     * @param categoryId Kategori ID'si.
     * @return Belirtilen kategoriye ait tüm ürünlerin listesini döner.
     */
    List<Product> findAllByCategory_Id(Long categoryId);
}
