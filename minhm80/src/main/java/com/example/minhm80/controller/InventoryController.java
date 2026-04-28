package com.example.minhm80.controller;

import com.example.minhm80.payload.dto.InventoryDTO;
import com.example.minhm80.payload.request.CreateInventoryRequest;
import com.example.minhm80.payload.request.UpdateInventoryRequest;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {


    private final InventoryService inventoryService;

    @PostMapping()
    public ResponseEntity<InventoryDTO> create(
            @RequestBody CreateInventoryRequest request
    ){
        InventoryDTO inventoryDTO = InventoryDTO.builder()
                .branchId(request.getBranchId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();

        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(
            @RequestBody UpdateInventoryRequest request,
            @PathVariable Long id
    ) throws Exception {
        InventoryDTO inventoryDTO = InventoryDTO.builder()
                .quantity(request.getQuantity())
                .build();

        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(

            @PathVariable Long id
    ) throws Exception {
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("delete successfully");
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDTO> getInventoryByProductAndBranchId(
            @PathVariable Long branchId,
            @PathVariable Long productId
    ){
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId, branchId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBranch(

            @PathVariable Long branchId
    ){
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }


}
