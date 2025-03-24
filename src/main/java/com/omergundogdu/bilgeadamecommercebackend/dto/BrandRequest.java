package com.omergundogdu.bilgeadamecommercebackend.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {
    private String name;
    private String description;
}