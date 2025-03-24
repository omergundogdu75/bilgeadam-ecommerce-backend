package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;

public interface CategoryWriteService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
}