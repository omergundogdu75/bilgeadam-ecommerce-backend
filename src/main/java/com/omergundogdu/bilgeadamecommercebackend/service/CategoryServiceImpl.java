package com.omergundogdu.bilgeadamecommercebackend.service;

import com.omergundogdu.bilgeadamecommercebackend.dto.CategoryChildResponse;
import com.omergundogdu.bilgeadamecommercebackend.dto.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.CategoryResponse;
import com.omergundogdu.bilgeadamecommercebackend.model.Category;
import com.omergundogdu.bilgeadamecommercebackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        String slug = (request.getSlug() == null || request.getSlug().isBlank())
                ? generateSlug(request.getName())
                : request.getSlug();

        category.setSlug(slug);
        category.setImageUrl(request.getImageUrl());


        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.getName());
        // Eğer slug null ya da boşsa otomatik oluştur
        String slug = (request.getSlug() == null || request.getSlug().isBlank())
                ? generateSlug(request.getName())
                : request.getSlug();

        category.setSlug(slug);
        category.setImageUrl(request.getImageUrl());

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getParent() == null)
                .map(this::convertToCategoryResponseWithChildren)
                .collect(Collectors.toList());
    }


    private String generateSlug(String name) {
        return name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "") // özel karakterleri temizle
                .replaceAll("\\s+", "-")       // boşlukları tire yap
                .trim();
    }


    private CategoryResponse convertToCategoryResponseWithChildren(Category category) {
        List<CategoryChildResponse> children = category.getChildren().stream()
                .map(child -> new CategoryChildResponse(child.getId(), child.getName(), child.getSlug(),child.getImageUrl()))
                .collect(Collectors.toList());

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .imageUrl(category.getImageUrl())
                .children(children)
                .build();
    }

}
