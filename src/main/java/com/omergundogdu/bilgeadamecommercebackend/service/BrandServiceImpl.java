package com.omergundogdu.bilgeadamecommercebackend.service;

import com.omergundogdu.bilgeadamecommercebackend.dto.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.BrandResponse;
import com.omergundogdu.bilgeadamecommercebackend.model.Brand;
import com.omergundogdu.bilgeadamecommercebackend.repository.BrandRepository;
import com.omergundogdu.bilgeadamecommercebackend.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Override
    public BrandResponse createBrand(BrandRequest request) {
        if (brandRepository.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Bu marka zaten mevcut");
        }
        Brand brand = modelMapper.map(request, Brand.class);
        return modelMapper.map(brandRepository.save(brand), BrandResponse.class);
    }

    @Override
    public BrandResponse updateBrand(Long id, BrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marka bulunamadı"));
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return modelMapper.map(brandRepository.save(brand), BrandResponse.class);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public BrandResponse getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(b -> modelMapper.map(b, BrandResponse.class))
                .orElseThrow(() -> new RuntimeException("Marka bulunamadı"));
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(b -> modelMapper.map(b, BrandResponse.class))
                .collect(Collectors.toList());
    }
}