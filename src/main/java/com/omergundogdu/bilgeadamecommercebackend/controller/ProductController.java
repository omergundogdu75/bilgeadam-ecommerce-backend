package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.ProductRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.ProductResponse;
import com.omergundogdu.bilgeadamecommercebackend.service.read.ProductReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.ProductWriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ürünlerle ilgili işlemleri yöneten REST controller sınıfıdır.
 * <p>
 * Ürün oluşturma, güncelleme, silme, tüm ürünleri ve kategorilere göre ürünleri listeleme gibi işlevleri içerir.
 * </p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Product API", description = "Ürünler ile ilgili işlemler")
public class ProductController {

    private final ProductReadService productReadService;
    private final ProductWriteService productWriteService;

    /**
     * Yeni bir ürün oluşturur.
     *
     * @param request Ürün bilgilerini içeren DTO
     * @return Oluşturulan ürün bilgisi
     */
    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(productWriteService.createProduct(request), HttpStatus.CREATED);
    }

    /**
     * Belirtilen ID'ye sahip ürünü günceller.
     *
     * @param id      Ürün ID'si
     * @param request Güncellenecek ürün bilgileri
     * @return Güncellenmiş ürün bilgisi
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productWriteService.updateProduct(id, request));
    }

    /**
     * Belirtilen ID'ye sahip ürünü siler.
     *
     * @param id Ürün ID'si
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productWriteService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Sistemdeki tüm ürünleri listeler.
     *
     * @return Ürün listesi
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(productReadService.getAllProducts());
    }

    /**
     * Belirli bir ID'ye sahip ürünü getirir.
     *
     * @param id Ürün ID'si
     * @return Ürün bilgisi
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productReadService.getProductById(id));
    }

    /**
     * Belirli bir kategoriye ait ürünleri listeler.
     *
     * @param categoryId Kategori ID'si
     * @return Kategoriye ait ürün listesi
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productReadService.getProductsByCategory(categoryId));
    }
}
