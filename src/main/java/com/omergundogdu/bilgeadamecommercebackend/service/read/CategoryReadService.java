package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;

import java.util.List;

/**
 * Kategori verilerinin okunmasıyla ilgili işlemleri tanımlayan arayüzdür.
 * Kategorileri ID ile veya tüm liste olarak getirme işlevlerini içerir.
 *
 * @author Ömer GÜNDOĞDU
 */
public interface CategoryReadService {

    /**
     * Verilen ID'ye sahip kategoriyi getirir.
     *
     * @param id Kategorinin benzersiz kimliği
     * @return İstenen kategoriye ait CategoryResponse
     */
    CategoryResponse getCategoryById(Long id);

    /**
     * Tüm kategorileri getirir.
     *
     * @return CategoryResponse nesnelerinden oluşan liste
     */
    List<CategoryResponse> getAllCategories();
}
