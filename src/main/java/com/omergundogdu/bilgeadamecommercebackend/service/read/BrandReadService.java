package com.omergundogdu.bilgeadamecommercebackend.service.read;

import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;

import java.util.List;

public interface BrandReadService {
    BrandResponse getBrandById(Long id);
    List<BrandResponse> getAllBrands();
}

