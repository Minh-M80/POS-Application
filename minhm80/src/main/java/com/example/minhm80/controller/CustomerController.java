package com.example.minhm80.controller;

import com.example.minhm80.modal.Customer;
import com.example.minhm80.payload.response.ApiResponse;
import com.example.minhm80.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(
            @PathVariable Long id,
            @RequestBody Customer customer
    ) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(
            @PathVariable Long id
    ) throws Exception {
        customerService.deleteCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer deleted");
      return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() throws Exception {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> search(
            @RequestParam String keyword
    ) throws Exception {
        return ResponseEntity.ok(customerService.searchCustomers(keyword));
    }





}
