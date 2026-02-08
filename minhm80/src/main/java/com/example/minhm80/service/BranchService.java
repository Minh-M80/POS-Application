package com.example.minhm80.service;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {


    BranchDTO createBranch(BranchDTO branchDTO, User user) throws UserException;
    BranchDTO updateBranch(Long id,BranchDTO branchDTO, User user) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDTO> getAllBranchesByStoreId(Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;

}
