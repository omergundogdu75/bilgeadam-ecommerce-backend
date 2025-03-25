package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.ProductRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.ProductResponse;
import com.omergundogdu.bilgeadamecommercebackend.model.Brand;
import com.omergundogdu.bilgeadamecommercebackend.model.Category;
import com.omergundogdu.bilgeadamecommercebackend.model.Product;
import com.omergundogdu.bilgeadamecommercebackend.repository.BrandRepository;
import com.omergundogdu.bilgeadamecommercebackend.repository.CategoryRepository;
import com.omergundogdu.bilgeadamecommercebackend.repository.ProductRepository;
import com.omergundogdu.bilgeadamecommercebackend.service.read.ProductReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.ProductWriteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Ürün işlemlerini gerçekleştiren servis katmanıdır.
 * Bu sınıf, ürün oluşturma, güncelleme, silme, listeleme gibi işlemleri içerir.
 * <p>
 * @author  Ömer GÜNDOĞDU
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductReadService, ProductWriteService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    /**
     * Yeni bir ürün oluşturur.
     *
     * @param request Ürün oluşturma isteği
     * @return Oluşturulan ürün bilgisi
     * @throws RuntimeException Kategori veya marka bulunamazsa
     */
    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        Product product = modelMapper.map(request, Product.class);
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Marka bulunamadı"));

        product.setBrand(brand);
        product.setCategory(category);
        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    /**
     * Belirtilen ID'ye sahip ürünü günceller.
     *
     * @param id      Güncellenecek ürünün ID'si
     * @param request Yeni ürün bilgileri
     * @return Güncellenmiş ürün bilgisi
     * @throws RuntimeException Ürün, kategori veya marka bulunamazsa
     */
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Marka bulunamadı"));

        product.setBrand(brand);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);

        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    /**
     * Belirtilen ID'ye sahip ürünü siler.
     *
     * @param id Silinecek ürünün ID'si
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Belirtilen ID'ye sahip ürünü getirir.
     *
     * @param id Ürün ID'si
     * @return Ürün bilgisi
     * @throws RuntimeException Ürün bulunamazsa
     */
    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
    }

    /**
     * Tüm ürünleri listeler.
     *
     * @return Ürünlerin listesi
     */
    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
    }

    /**
     * Belirtilen kategori ID'sine göre ürünleri listeler.
     *
     * @param categoryId Kategori ID'si
     * @return Ürünlerin listesi
     */
    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId)
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
    }
}
