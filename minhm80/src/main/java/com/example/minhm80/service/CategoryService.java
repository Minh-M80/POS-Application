package com.example.minhm80.service;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto) throws Exception;

    List<CategoryDTO> getCategoriesByStore(Long storeId);

    CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception;

    void deleteCategory(long id) throws Exception;
}
