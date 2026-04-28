package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class CreateInventoryRequest {
    private Long branchId;
    private Long productId;
    private Integer quantity;
}
