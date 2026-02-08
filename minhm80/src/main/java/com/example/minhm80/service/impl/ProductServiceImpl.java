package com.example.minhm80.service.impl;

import com.example.minhm80.mapper.ProductMapper;
import com.example.minhm80.modal.Category;
import com.example.minhm80.modal.Product;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.ProductDTO;
import com.example.minhm80.repository.CategoryRepository;
import com.example.minhm80.repository.ProductRepository;
import com.example.minhm80.repository.StoreRepository;
import com.example.minhm80.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;



    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
        Store store =storeRepository.findById(productDTO.getStoreId()).orElseThrow(
                ()->new Exception("Store not found")
        );

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                ()->new Exception("Category not found")
        );

        Product product = ProductMapper.toEntity(productDTO,store,category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );








        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(productDTO.getBrand());
        product.setUpdatedAt(productDTO.getUpdatedAt());

        if(productDTO.getCategoryId() != null){
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    ()-> new Exception("category not found")
            );

            product.setCategory(category);

        }

        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found")
        );

        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {

         List<Product> products =productRepository.findByStoreId(storeId);
         return  products.stream()
                 .map(ProductMapper::toDTO)
                 .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product> products =productRepository.searchByKeyword(storeId,keyword);
        return  products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());

    }
}
