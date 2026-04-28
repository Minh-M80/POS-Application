package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name;
    private String sku;
    private String description;
    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String image;
    private Long categoryId;
}
