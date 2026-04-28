package com.example.minhm80.controller;

import com.example.minhm80.domain.StoreStatus;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.StoreMapper;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.StoreDTO;
import com.example.minhm80.payload.request.CreateStoreRequest;
import com.example.minhm80.payload.request.UpdateStoreRequest;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.StoreService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@RequestBody CreateStoreRequest request) throws UserException {
        User user = userService.getCurrentUser();

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setBrand(request.getBrand());
        storeDTO.setDescription(request.getDescription());
        storeDTO.setStoreType(request.getStoreType());
        storeDTO.setContact(request.getContact());

        return ResponseEntity.ok(storeService.createStore(storeDTO, user));
    }



    @GetMapping()
    public ResponseEntity<List<StoreDTO>> getAllStore() throws Exception {

        return ResponseEntity.ok(storeService.getAllStores());
    }


    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin() throws Exception {

        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));
    }


    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee() throws Exception {

        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
            @PathVariable Long id,
            @RequestBody UpdateStoreRequest request
    ) throws Exception {

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setBrand(request.getBrand());
        storeDTO.setDescription(request.getDescription());
        storeDTO.setStoreType(request.getStoreType());
        storeDTO.setContact(request.getContact());

        return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
    }

    @PutMapping ("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(
            @PathVariable Long id,
            @RequestParam StoreStatus status

    ) throws Exception {

        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }

    @GetMapping("{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(storeService.getStoreById(id));
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(
            @PathVariable Long id
    ) throws Exception {

        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("store deleted successfully");

        return ResponseEntity.ok(apiResponse);
    }

}
