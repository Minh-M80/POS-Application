package com.example.minhm80.payload.dto;

import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class InventoryDTO {
    private Long id;

    private BranchDTO branch;

    private Long branchId;
    private Long productId;

    private ProductDTO product;


    private Integer quantity;


    private LocalDateTime lastUpdate;
}
