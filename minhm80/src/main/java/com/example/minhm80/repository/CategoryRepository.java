package com.example.minhm80.repository;

import com.example.minhm80.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByStoreId(Long storeId);

}
