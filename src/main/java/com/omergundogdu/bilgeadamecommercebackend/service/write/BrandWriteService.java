package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;

/**
 * Marka verileri üzerinde yazma (oluşturma, güncelleme, silme) işlemlerini tanımlayan servis arayüzüdür.
 * <p>
 * Geliştirici: Ömer GÜNDOĞDU
 */
public interface BrandWriteService {

    /**
     * Yeni bir marka oluşturur.
     *
     * @param request Oluşturulacak marka bilgileri
     * @return Oluşturulan marka bilgisi
     */
    BrandResponse createBrand(BrandRequest request);

    /**
     * Belirtilen ID'ye sahip markayı günceller.
     *
     * @param id      Güncellenecek markanın ID'si
     * @param request Yeni marka bilgileri
     * @return Güncellenmiş marka bilgisi
     */
    BrandResponse updateBrand(Long id, BrandRequest request);

    /**
     * Belirtilen ID'ye sahip markayı siler.
     *
     * @param id Silinecek markanın ID'si
     */
    void deleteBrand(Long id);
}
