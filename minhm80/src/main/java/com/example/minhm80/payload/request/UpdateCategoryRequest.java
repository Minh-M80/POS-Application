package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
    private String name;
    private Long storeId;
}
