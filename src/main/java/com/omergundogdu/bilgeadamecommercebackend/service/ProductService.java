package com.omergundogdu.bilgeadamecommercebackend.service;

import com.omergundogdu.bilgeadamecommercebackend.dto.ProductRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(Long categoryId);
}

