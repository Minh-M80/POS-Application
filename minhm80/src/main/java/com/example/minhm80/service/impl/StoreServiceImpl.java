package com.example.minhm80.service.impl;

import com.example.minhm80.domain.StoreStatus;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.StoreMapper;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.StoreContact;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.StoreDTO;
import com.example.minhm80.repository.StoreRepository;
import com.example.minhm80.service.StoreService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {
        Store store = StoreMapper.toEntity(storeDTO,user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new UserException("Store not found", HttpStatus.NOT_FOUND)
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> dtos=  storeRepository.findAll();
       return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(admin.getId());
        if (store == null) {
            throw new UserException("store not found", HttpStatus.NOT_FOUND);
        }
        return store;
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) throws UserException {
        User currentUser = userService.getCurrentUser();

        Store existing = storeRepository.findById(id).orElseThrow(
                () -> new UserException("store not found", HttpStatus.NOT_FOUND)
        );

        if (!existing.getStoreAdmin().getId().equals(currentUser.getId())) {
            throw new UserException("you don't have permission to update this store", HttpStatus.FORBIDDEN);
        }

        existing.setBrand(storeDTO.getBrand());
        existing.setDescription(storeDTO.getDescription());

        if(storeDTO.getStoreType() != null){
            existing.setStoreType(storeDTO.getStoreType());
        }

        if(storeDTO.getContact() != null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDTO.getContact().getAddress())
                    .phone(storeDTO.getContact().getPhone())
                    .email(storeDTO.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
        Store updateStore = storeRepository.save(existing);

        return StoreMapper.toDTO(updateStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new UserException("store not found", HttpStatus.NOT_FOUND)
        );
        if (!store.getStoreAdmin().getId().equals(currentUser.getId())) {
            throw new UserException("you don't have permission to delete this store", HttpStatus.FORBIDDEN);
        }

        storeRepository.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();

        if(currentUser == null){
            throw new UserException("you don't have permission to access this store", HttpStatus.FORBIDDEN);
        }

        if (currentUser.getStore() == null) {
            throw new UserException("store not found", HttpStatus.NOT_FOUND);
        }

        return StoreMapper.toDTO(currentUser.getStore());

    }

    @Override
    public StoreDTO moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()->new UserException("store not found...", HttpStatus.NOT_FOUND)
        );

        store.setStatus(status);

        Store updatedStore = storeRepository.save(store);

        return StoreMapper.toDTO(updatedStore);

    }
}
