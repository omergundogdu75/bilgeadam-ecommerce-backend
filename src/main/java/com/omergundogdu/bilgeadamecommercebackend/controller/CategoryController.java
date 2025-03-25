package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;
import com.omergundogdu.bilgeadamecommercebackend.service.read.CategoryReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.CategoryWriteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kategori (Category) ile ilgili CRUD işlemlerini yöneten REST controller sınıfıdır.
 * <p>
 * Kategori oluşturma, güncelleme, silme, listeleme ve ID'ye göre getirme işlemlerini sağlar.
 * </p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin
@Validated
@Tag(name = "Category API", description = "Kategori işlemleri ile ilgili API")
public class CategoryController {

    private final CategoryReadService categoryReadService;
    private final CategoryWriteService categoryWriteService;

    /**
     * Yeni bir kategori oluşturur.
     *
     * @param request Yeni kategori bilgilerini içeren DTO
     * @return Oluşturulan kategorinin response DTO'su
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        return new ResponseEntity<>(categoryWriteService.createCategory(request), HttpStatus.CREATED);
    }

    /**
     * Belirtilen ID'ye sahip kategoriyi günceller.
     *
     * @param id      Güncellenecek kategorinin ID'si
     * @param request Yeni kategori bilgileri
     * @return Güncellenmiş kategori bilgileri
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryWriteService.updateCategory(id, request));
    }

    /**
     * Belirtilen ID'ye sahip kategoriyi siler.
     *
     * @param id Silinecek kategorinin ID'si
     * @return 204 No Content (Başarıyla silindi)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryWriteService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Tüm kategorileri listeler (alt kategoriler dahil).
     *
     * @return Kategori listesi
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryReadService.getAllCategories());
    }

    /**
     * Belirli bir kategoriyi ID'ye göre getirir.
     *
     * @param id Kategori ID'si
     * @return Kategori detayları
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryReadService.getCategoryById(id));
    }
}
