package com.example.minhm80.service.impl;

import com.example.minhm80.modal.Customer;
import com.example.minhm80.repository.CustomerRepository;
import com.example.minhm80.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found")
        );
        customerToUpdate.setFullName(customer.getFullName());
        customerToUpdate.setEmail(customerToUpdate.getEmail());
        customerToUpdate.setPhone(customerToUpdate.getPhone());


        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found")
        );
        customerRepository.delete(customerToUpdate);
    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found")
        );
    }

    @Override
    public List<Customer> getAllCustomer() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword,keyword
        );
    }
}
