package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String name;
    private Long storeId;
}
