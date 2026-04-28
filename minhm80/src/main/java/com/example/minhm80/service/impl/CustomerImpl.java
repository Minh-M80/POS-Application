package com.example.minhm80.service.impl;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.Customer;
import com.example.minhm80.repository.CustomerRepository;
import com.example.minhm80.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override


    @CachePut(value = "customers", key = "#result.id")
    @CacheEvict(value = "customerList", allEntries = true)
    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    @CachePut(value = "customers", key = "#id")
    @CacheEvict(value = "customerList", allEntries = true)

    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new UserException("Customer not found", HttpStatus.NOT_FOUND)
        );
        customerToUpdate.setFullName(customer.getFullName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPhone(customer.getPhone());


        return customerRepository.save(customerToUpdate);
    }

    @Override

    @Caching(evict = {
            @CacheEvict(value = "customers", key = "#id"),
            @CacheEvict(value = "customerList", allEntries = true)
    })
    public void deleteCustomer(Long id) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new UserException("Customer not found", HttpStatus.NOT_FOUND)
        );
        customerRepository.delete(customerToUpdate);
    }

    @Override
    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(
                () -> new UserException("Customer not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @Cacheable(value = "customerList", key = "'all'")
    public List<Customer> getAllCustomer() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    @Cacheable(value = "customerSearch", key = "#keyword")
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword,keyword
        );
    }
}
