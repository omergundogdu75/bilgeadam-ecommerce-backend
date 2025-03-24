package com.omergundogdu.bilgeadamecommercebackend.service;

import com.omergundogdu.bilgeadamecommercebackend.dto.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);
    List<CategoryResponse> getAllCategories();
}

