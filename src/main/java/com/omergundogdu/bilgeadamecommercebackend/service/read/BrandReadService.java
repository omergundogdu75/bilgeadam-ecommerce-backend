package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;

import java.util.List;

/**
 * Marka verilerini okuma işlemlerini gerçekleştiren servis arayüzüdür.
 * <p>
 * Bu arayüz, markaların listelenmesi ve ID'ye göre getirilmesi gibi işlemleri tanımlar.
 * <p>
 * Geliştirici: Ömer GÜNDOĞDU
 */
public interface BrandReadService {

    /**
     * Belirtilen ID'ye sahip markayı döner.
     *
     * @param id Marka ID'si
     * @return Marka bilgisi
     */
    BrandResponse getBrandById(Long id);

    /**
     * Tüm markaların listesini döner.
     *
     * @return Marka listesi
     */
    List<BrandResponse> getAllBrands();
}
