package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;
import com.omergundogdu.bilgeadamecommercebackend.service.read.CategoryReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.CategoryWriteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class CategoryController {

    private final CategoryReadService categoryReadService;
    private final CategoryWriteService categoryWriteService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        return new ResponseEntity<>(categoryWriteService.createCategory(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryWriteService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryWriteService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryReadService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryReadService.getCategoryById(id));
    }
}
