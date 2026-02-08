package com.example.minhm80.mapper;

import com.example.minhm80.modal.Category;
import com.example.minhm80.payload.dto.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore() != null ? category.getStore().getId() : null)
                .build();
    }
}
