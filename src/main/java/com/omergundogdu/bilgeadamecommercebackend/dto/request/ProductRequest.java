package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String imageUrl;
    private Long categoryId; // sadece id gelecek
    private Long brandId;

}

