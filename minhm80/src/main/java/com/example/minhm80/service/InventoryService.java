package com.example.minhm80.service;

import java.util.List;

import com.example.minhm80.payload.dto.InventoryDTO;

public interface InventoryService {
    InventoryDTO createInventory(InventoryDTO inventoryDTO);
    InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO) throws Exception;

    void deleteInventory(Long id) throws Exception;
    InventoryDTO getInventoryById(Long id) throws Exception;
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId,Long branchId);

    List<InventoryDTO> getAllInventoryByBranchId(Long branchId);
}
