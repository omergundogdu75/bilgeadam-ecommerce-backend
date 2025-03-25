package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;
import com.omergundogdu.bilgeadamecommercebackend.service.read.BrandReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.BrandWriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Marka (Brand) ile ilgili CRUD işlemlerini yöneten controller sınıfıdır.
 * <p>
 * Markaları oluşturma, güncelleme, silme, listeleme ve tekil getirme işlemleri içerir.
 * </p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Brand API", description = "Marka işlemleri ile ilgili API")
public class BrandController {

    private final BrandReadService brandReadService;
    private final BrandWriteService brandWriteService;

    /**
     * Yeni bir marka oluşturur.
     *
     * @param request Marka bilgileri (name, description vs.)
     * @return Oluşturulan markanın bilgileri
     */
    @PostMapping
    public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest request) {
        return new ResponseEntity<>(brandWriteService.createBrand(request), HttpStatus.CREATED);
    }

    /**
     * Var olan bir markayı günceller.
     *
     * @param id      Güncellenecek markanın ID'si
     * @param request Yeni marka bilgileri
     * @return Güncellenmiş marka bilgileri
     */
    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@PathVariable Long id, @RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandWriteService.updateBrand(id, request));
    }

    /**
     * ID'ye göre bir markayı siler.
     *
     * @param id Silinecek markanın ID'si
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandWriteService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Tüm markaları listeler.
     *
     * @return Marka listesi
     */
    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(brandReadService.getAllBrands());
    }

    /**
     * Belirli bir markayı ID ile getirir.
     *
     * @param id Marka ID'si
     * @return İlgili markanın bilgileri
     */
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(brandReadService.getBrandById(id));
    }
}
