package com.omergundogdu.bilgeadamecommercebackend.dto;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String slug;
    private Long parentId;
    private String imageUrl;

}
