package com.example.minhm80.mapper;

import com.example.minhm80.modal.Category;
import com.example.minhm80.modal.Product;
import com.example.minhm80.modal.Store;
import com.example.minhm80.payload.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO toDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .category(CategoryMapper.toDTO(product.getCategory()))
                .storeId(product.getStore() != null? product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();


    }

    public static Product toEntity(ProductDTO productDTO,
                                   Store store,
                                   Category category){
        return Product.builder()
                .name(productDTO.getName())
                .store(store)
                .category(category)
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())

                .build();
    }
}
