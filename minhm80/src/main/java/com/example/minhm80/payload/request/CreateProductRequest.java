package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class CreateProductRequest {
    private String name;
    private String sku;
    private String description;
    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String image;
    private Long categoryId;
    private Long storeId;
}
