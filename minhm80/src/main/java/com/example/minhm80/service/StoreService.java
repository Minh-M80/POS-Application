package com.example.minhm80.service;

import com.example.minhm80.domain.StoreStatus;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    StoreDTO createStore(StoreDTO storeDTO, User user);
    StoreDTO getStoreById(Long id) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO updateStore(Long id,StoreDTO storeDTO) throws UserException;
    void deleteStore(Long id) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;

    StoreDTO moderateStore(Long id, StoreStatus status) throws Exception;
}
