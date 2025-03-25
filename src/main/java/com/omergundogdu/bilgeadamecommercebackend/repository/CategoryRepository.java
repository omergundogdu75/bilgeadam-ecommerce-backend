package com.omergundogdu.bilgeadamecommercebackend.repository;

import com.omergundogdu.bilgeadamecommercebackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Kategori veritabanı işlemleri için kullanılan repository arayüzü.
 * <p>
 * Bu arayüz, {@link Category} modeline yönelik temel CRUD (Create, Read, Update, Delete) işlemleri sağlar.
 * Ayrıca, kategorilerle ilgili bazı özel sorgu yöntemlerini içerir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Sadece ana kategorileri (parent'ı null olanları) döner.
     * <p>
     * Bu metod, ana kategorileri almak için kullanılır; alt kategorileri içermeyen kategorileri getirir.
     * </p>
     *
     * @return Ana kategoriler listesini döner.
     */
    List<Category> findAllByParentIsNull();
}
