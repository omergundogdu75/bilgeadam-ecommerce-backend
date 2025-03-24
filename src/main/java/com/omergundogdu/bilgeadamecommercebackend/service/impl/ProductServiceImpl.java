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

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductReadService, ProductWriteService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

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

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId)
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
    }
}

