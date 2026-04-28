package com.example.minhm80.controller;

import com.example.minhm80.domain.UserRole;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.UserDto;
import com.example.minhm80.payload.request.CreateEmployeeRequest;
import com.example.minhm80.payload.request.UpdateEmployeeRequest;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")

public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(
            @PathVariable Long storeId,
           @RequestBody CreateEmployeeRequest request) throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFullName(request.getFullName());
        userDto.setEmail(request.getEmail());
        userDto.setPhone(request.getPhone());
        userDto.setPassword(request.getPassword());
        userDto.setRole(request.getRole());

        UserDto employee = employeeService.createStoreEmployee(userDto, storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(
            @PathVariable Long branchId,
            @RequestBody CreateEmployeeRequest request) throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFullName(request.getFullName());
        userDto.setEmail(request.getEmail());
        userDto.setPhone(request.getPhone());
        userDto.setPassword(request.getPassword());
        userDto.setRole(request.getRole());

        UserDto employee = employeeService.createBranchEmployee(userDto, branchId);
        return ResponseEntity.ok(employee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(
            @PathVariable Long id,
            @RequestBody UpdateEmployeeRequest request) throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFullName(request.getFullName());
        userDto.setEmail(request.getEmail());
        userDto.setPhone(request.getPhone());
        userDto.setPassword(request.getPassword());
        userDto.setRole(request.getRole());
        userDto.setBranchId(request.getBranchId());

        User employee = employeeService.updateEmployee(id, userDto);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable Long id) throws Exception {
      employeeService.deleteEmployee(id);
      ApiResponse apiResponse = new ApiResponse();
      apiResponse.setMessage("Employee deleted");

      return  ResponseEntity.ok(apiResponse);
    }



    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDto>> storeEmployee(
            @PathVariable Long id,
            @RequestParam(required = false)UserRole userRole
    ) throws Exception {
        List<UserDto> employee = employeeService.findStoreEmployees(id, userRole);

        return ResponseEntity.ok(employee);
    }


    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDto>> branchEmployee(
            @PathVariable Long id,
            @RequestParam(required = false)UserRole userRole
    ) throws Exception {
        List<UserDto> employee = employeeService.findBranchEmployees(id, userRole);

        return ResponseEntity.ok(employee);
    }


}
