package com.example.minhm80.service.impl;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.BranchMapper;
import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.BranchDTO;
import com.example.minhm80.repository.BranchRepository;
import com.example.minhm80.repository.StoreRepository;
import com.example.minhm80.repository.UserRepository;
import com.example.minhm80.service.BranchService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BranchServiceIpml implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;



    @Override
    public BranchDTO createBranch(BranchDTO branchDTO, User user) throws UserException {
        User currentUser = userService.getCurrentUser();

        Store store = storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDTO,store);

        Branch savedBranch = branchRepository.save(branch);
        return BranchMapper.toDTO(savedBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO, User user) throws Exception {

        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch not exist..")
        );

        existing.setName(branchDTO.getName());
        existing.setWorkingDays(branchDTO.getWorkingDays());
        existing.setEmail(branchDTO.getEmail());
        existing.setPhone(branchDTO.getPhone());
        existing.setAddress(branchDTO.getAddress());
        existing.setOpenTime(branchDTO.getOpenTime());
        existing.setCloseTime(branchDTO.getCloseTime());
        existing.setUpdatedAt(branchDTO.getUpdatedAt());

        Branch updatedBranch = branchRepository.save(existing);

        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch not exist..")
        );
        branchRepository.delete(existing);
    }


    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {
        List<Branch> branches = branchRepository.findByStoreId(storeId);
       return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());



    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch not exist..")
        );

        return BranchMapper.toDTO(existing);
    }
}
