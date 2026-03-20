package com.example.minhm80.service.impl;

import com.example.minhm80.domain.UserRole;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.UserMapper;
import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.UserDto;
import com.example.minhm80.repository.BranchRepository;
import com.example.minhm80.repository.StoreRepository;
import com.example.minhm80.repository.UserRepository;
import com.example.minhm80.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()-> new UserException("Store not found", HttpStatus.NOT_FOUND)
        );

        Branch branch = null;

        if(employee.getRole() ==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId() == null){
                throw new UserException("Branch id is required to create branch manager", HttpStatus.BAD_REQUEST);
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    ()-> new UserException("branch not found", HttpStatus.NOT_FOUND)
            );

        }
        User user = UserMapper.toEntity(employee);

        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee =userRepository.save(user);

        if(employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch != null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }

        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {

       Branch branch = branchRepository.findById(branchId).orElseThrow(
                ()-> new UserException("branch not found", HttpStatus.NOT_FOUND)
        );

       //ADMIN

       if(employee.getRole() == UserRole.ROLE_BRANCH_CASHIER ||
               employee.getRole() == UserRole.ROLE_BRANCH_MANAGER
       ){
           User user = UserMapper.toEntity(employee);
           user.setBranch(branch);
           user.setPassword(passwordEncoder.encode(employee.getPassword()));
           return UserMapper.toDTO(userRepository.save(user));
       }
       throw new UserException("Branch role not supported", HttpStatus.BAD_REQUEST);






    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {

        User existingEmployee = userRepository.findById(employeeId).orElseThrow(
                ()-> new UserException("employee not exist with given id", HttpStatus.NOT_FOUND)
        );

        Branch branch =branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                ()->new UserException("branch not found", HttpStatus.NOT_FOUND)
        );
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);




        return userRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(
                ()->new UserException("employee not found", HttpStatus.NOT_FOUND)
        );

        userRepository.delete(employee);

    }

    @Override
    public List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new UserException("store not found", HttpStatus.NOT_FOUND)
        );
        return userRepository.findByStore(store).stream().filter(
                user -> role == null || user.getRole() == role
        )
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                () -> new UserException("branch not found", HttpStatus.NOT_FOUND)
        );

        return userRepository.findByBranchId(branchId).stream().filter(
                user -> role==null || user.getRole()==role
        )
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
