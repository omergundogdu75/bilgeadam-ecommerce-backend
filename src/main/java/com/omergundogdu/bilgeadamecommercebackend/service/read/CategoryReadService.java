package com.omergundogdu.bilgeadamecommercebackend.service.read;


import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryReadService {
    CategoryResponse getCategoryById(Long id);
    List<CategoryResponse> getAllCategories();
}

