package com.omergundogdu.bilgeadamecommercebackend.service;

import com.omergundogdu.bilgeadamecommercebackend.dto.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse createBrand(BrandRequest request);
    BrandResponse updateBrand(Long id, BrandRequest request);
    void deleteBrand(Long id);
    BrandResponse getBrandById(Long id);
    List<BrandResponse> getAllBrands();
}
