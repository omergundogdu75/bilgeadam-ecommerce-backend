package com.omergundogdu.bilgeadamecommercebackend.repository;


import com.omergundogdu.bilgeadamecommercebackend.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByNameIgnoreCase(String name);
}
