package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.ProductRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.ProductResponse;

/**
 * Ürün verileri üzerinde yazma (oluşturma, güncelleme, silme) işlemlerini tanımlayan servis arayüzüdür.
 * <p>
 * Geliştirici: Ömer GÜNDOĞDU
 */
public interface ProductWriteService {

    /**
     * Yeni bir ürün oluşturur.
     *
     * @param request Oluşturulacak ürün bilgileri
     * @return Oluşturulan ürün bilgisi
     */
    ProductResponse createProduct(ProductRequest request);

    /**
     * Belirtilen ID'ye sahip ürünü günceller.
     *
     * @param id      Güncellenecek ürünün ID'si
     * @param request Yeni ürün bilgileri
     * @return Güncellenmiş ürün bilgisi
     */
    ProductResponse updateProduct(Long id, ProductRequest request);

    /**
     * Belirtilen ID'ye sahip ürünü siler.
     *
     * @param id Silinecek ürünün ID'si
     */
    void deleteProduct(Long id);
}
