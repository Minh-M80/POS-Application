package com.example.minhm80.repository;

import com.example.minhm80.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {


    Store findByStoreAdminId(Long id);
}
