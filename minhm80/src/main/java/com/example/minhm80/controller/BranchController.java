package com.example.minhm80.controller;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.payload.dto.BranchDTO;
import com.example.minhm80.payload.request.CreateBranchRequest;
import com.example.minhm80.payload.request.UpdateBranchRequest;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody CreateBranchRequest request) throws UserException {
        BranchDTO branchDTO = BranchDTO.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .workingDays(request.getWorkingDays())
                .openTime(request.getOpenTime())
                .closeTime(request.getCloseTime())
                .storeId(request.getStoreId())
                .build();

        BranchDTO createdBranch = branchService.createBranch(branchDTO);
        return ResponseEntity.ok(createdBranch);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(
            @PathVariable Long id
    ) throws Exception {
        BranchDTO createdBranch = branchService.getBranchById(id);
        return ResponseEntity.ok(createdBranch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchByStoreId(
            @PathVariable Long storeId
    ) throws Exception {
        List<BranchDTO> createdBranch = branchService.getAllBranchesByStoreId(storeId);
        return ResponseEntity.ok(createdBranch);
    }



    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(
            @PathVariable Long id,
            @RequestBody UpdateBranchRequest request
    ) throws Exception {
        BranchDTO branchDTO = BranchDTO.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .workingDays(request.getWorkingDays())
                .openTime(request.getOpenTime())
                .closeTime(request.getCloseTime())
                .storeId(request.getStoreId())
                .build();

        BranchDTO createdBranch = branchService.updateBranch(id, branchDTO);
        return ResponseEntity.ok(createdBranch);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranchById(
            @PathVariable Long id

    ) throws Exception {

        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("delete branch successfully");

        return ResponseEntity.ok(apiResponse);
    }


}
