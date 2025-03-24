package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.ProductRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.ProductResponse;

public interface ProductWriteService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
