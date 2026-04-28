package com.example.minhm80.controller;


import com.example.minhm80.payload.dto.CategoryDTO;
import com.example.minhm80.payload.request.CreateCategoryRequest;
import com.example.minhm80.payload.request.UpdateCategoryRequest;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private  final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(
            @RequestBody CreateCategoryRequest request) throws Exception {

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(request.getName())
                .storeId(request.getStoreId())
                .build();

        return ResponseEntity.ok(
                categoryService.createCategory(categoryDTO)
        );
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByStoreId(
            @PathVariable Long storeId) throws Exception {

        return ResponseEntity.ok(
                categoryService.getCategoriesByStore(storeId)
        );
    }

    @PutMapping ("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @RequestBody UpdateCategoryRequest request,
            @PathVariable Long id) throws Exception {

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(request.getName())
                .storeId(request.getStoreId())
                .build();

        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryDTO)
        );
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(

            @PathVariable Long id) throws Exception {

        categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("category deleted successfully");
        return ResponseEntity.ok(
                apiResponse
        );
    }



}
