package com.example.minhm80.mapper;

import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.StoreDTO;

public class StoreMapper {



    public static StoreDTO toDTO(Store store){
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(store.getId());
        storeDTO.setBrand(store.getBrand());
        storeDTO.setDescription(store.getDescription());
        storeDTO.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDTO.setStoreType(store.getStoreType());
        storeDTO.setContact(store.getContact());
        storeDTO.setCreatedAt(store.getCreatedAt());
        storeDTO.setCreatedAt(store.getUpdateAt());
        storeDTO.setStatus(store.getStatus());





        return storeDTO;
    }

    public static Store toEntity(StoreDTO storeDTO, User storeAdmin){
        Store store =new Store();
        store.setId(storeDTO.getId());
        store.setBrand(storeDTO.getBrand());
        store.setDescription(storeDTO.getDescription());
        store.setStoreAdmin(storeAdmin);
        store.setStoreType(storeDTO.getStoreType());
        store.setContact(storeDTO.getContact());
        store.setCreatedAt(storeDTO.getCreatedAt());
        store.setUpdateAt(storeDTO.getUpdateAt());
        return store;
    }
}


