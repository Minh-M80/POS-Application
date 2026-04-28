package com.example.minhm80.controller;

import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.ProductDTO;
import com.example.minhm80.payload.request.CreateProductRequest;
import com.example.minhm80.payload.request.UpdateProductRequest;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.ProductService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody CreateProductRequest request) throws Exception {
        User user = userService.getCurrentUser();

        ProductDTO productDTO = ProductDTO.builder()
                .name(request.getName())
                .sku(request.getSku())
                .description(request.getDescription())
                .mrp(request.getMrp())
                .sellingPrice(request.getSellingPrice())
                .brand(request.getBrand())
                .image(request.getImage())
                .categoryId(request.getCategoryId())
                .storeId(request.getStoreId())
                .build();

        return ResponseEntity.ok(
                productService.createProduct(
                        productDTO, user
                )
        );
    }


    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDTO>> getByStoreId(@PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(
                productService.getProductsByStoreId(
                        storeId
                )
        );
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                             @RequestBody UpdateProductRequest request) throws Exception {
        User user = userService.getCurrentUser();

        ProductDTO productDTO = ProductDTO.builder()
                .name(request.getName())
                .sku(request.getSku())
                .description(request.getDescription())
                .mrp(request.getMrp())
                .sellingPrice(request.getSellingPrice())
                .brand(request.getBrand())
                .image(request.getImage())
                .categoryId(request.getCategoryId())
                .build();

        return ResponseEntity.ok(
                productService.updateProduct(
                        id,
                        productDTO,
                        user
                )
        );
    }

    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDTO>> searchByKeyword(
            @PathVariable Long storeId,
            @RequestParam String keyword) throws Exception {
        return ResponseEntity.ok(
                productService.searchByKeyword(
                        storeId,
                        keyword
                )
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
        User user = userService.getCurrentUser();
        productService.deleteProduct(
                id,
                user
        );

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product deleted successfully");

        return ResponseEntity.ok(
                apiResponse
        );
    }

}
