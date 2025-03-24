package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;

public interface BrandWriteService {
    BrandResponse createBrand(BrandRequest request);
    BrandResponse updateBrand(Long id, BrandRequest request);
    void deleteBrand(Long id);
}
