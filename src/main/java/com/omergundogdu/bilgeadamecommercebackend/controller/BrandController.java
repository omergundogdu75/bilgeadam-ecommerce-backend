package com.omergundogdu.bilgeadamecommercebackend.controller;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;
import com.omergundogdu.bilgeadamecommercebackend.service.read.BrandReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.BrandWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin
public class BrandController {

    private final BrandReadService brandReadService;
    private final BrandWriteService brandWriteService;

    @PostMapping
    public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest request) {
        return new ResponseEntity<>(brandWriteService.createBrand(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@PathVariable Long id, @RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandWriteService.updateBrand(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandWriteService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(brandReadService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(brandReadService.getBrandById(id));
    }
}
