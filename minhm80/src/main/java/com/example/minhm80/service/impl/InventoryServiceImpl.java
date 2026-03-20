/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.minhm80.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.InventoryMapper;
import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Inventory;
import com.example.minhm80.modal.Product;
import com.example.minhm80.payload.dto.InventoryDTO;
import com.example.minhm80.repository.BranchRepository;
import com.example.minhm80.repository.InventoryRepository;
import com.example.minhm80.repository.ProductRepository;
import com.example.minhm80.service.InventoryService;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author admin
 */
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Branch branch = branchRepository.findById(inventoryDTO.getBranchId())
                .orElseThrow(() -> new UserException("Branch not found with id: " + inventoryDTO.getBranchId(), HttpStatus.NOT_FOUND));

        Product product = productRepository.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new UserException("Product not found with id: " + inventoryDTO.getProductId(), HttpStatus.NOT_FOUND));

        Inventory inventory = InventoryMapper.toEntity(inventoryDTO, branch, product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO) throws Exception {
            Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new UserException("Inventory not found", HttpStatus.NOT_FOUND));

            inventory.setQuantity(inventoryDTO.getQuantity());
            Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updatedInventory);
                    
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new UserException("Inventory not found", HttpStatus.NOT_FOUND));
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new UserException("Inventory not found", HttpStatus.NOT_FOUND));
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId,branchId);

        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(
                InventoryMapper::toDTO
        ).collect(Collectors.toList());

    }

}
