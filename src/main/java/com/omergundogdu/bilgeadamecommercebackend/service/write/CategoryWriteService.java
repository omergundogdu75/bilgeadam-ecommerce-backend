package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;

/**
 * Kategori verileri üzerinde yazma (oluşturma, güncelleme, silme) işlemlerini tanımlayan servis arayüzüdür.
 * Bu servis, admin paneli gibi yönetim işlemleri için kullanılır.
 *
 * @author Ömer GÜNDOĞDU
 */
public interface CategoryWriteService {

    /**
     * Yeni bir kategori oluşturur.
     *
     * @param request Oluşturulacak kategoriye ait veriler
     * @return Oluşturulan kategoriye ait yanıt nesnesi
     */
    CategoryResponse createCategory(CategoryRequest request);

    /**
     * Belirtilen ID'ye sahip kategoriyi günceller.
     *
     * @param id Güncellenecek kategorinin kimliği
     * @param request Güncellenmiş kategori verileri
     * @return Güncellenmiş kategoriye ait yanıt nesnesi
     */
    CategoryResponse updateCategory(Long id, CategoryRequest request);

    /**
     * Belirtilen ID'ye sahip kategoriyi siler.
     *
     * @param id Silinecek kategorinin kimliği
     */
    void deleteCategory(Long id);
}
