package com.example.minhm80.service.impl;

import com.example.minhm80.domain.UserRole;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.CategoryMapper;
import com.example.minhm80.mapper.ProductMapper;
import com.example.minhm80.modal.Category;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.CategoryDTO;
import com.example.minhm80.repository.CategoryRepository;
import com.example.minhm80.repository.StoreRepository;
import com.example.minhm80.repository.UserRepository;
import com.example.minhm80.service.CategoryService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;



    @Override
    public CategoryDTO createCategory(CategoryDTO dto) throws Exception {
        User user = userService.getCurrentUser();

        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
                ()-> new Exception("Store not found")
        );

        Category category = Category.builder()

                .store(store)
                .name(dto.getName())

                .build();

        checkAuthority(user,category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);

        return categories.stream().map(
                CategoryMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("category not exist")
        );

        User user =userService.getCurrentUser();

        category.setName(dto.getName());
        checkAuthority(user,category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("category not exist")
        );
        User user =userService.getCurrentUser();

        checkAuthority(user,category.getStore());

        categoryRepository.delete(category);

    }

    private void checkAuthority(User user,Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);

        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isSameStore = user.equals(store.getStoreAdmin());



        if(!(isAdmin && isSameStore) && !isManager){
            throw  new Exception("you don't have permission to manager this category");
        }







        //raam => storeAdmin
        // x => raam

    }


}
