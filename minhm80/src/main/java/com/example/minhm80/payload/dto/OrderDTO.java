package com.example.minhm80.payload.dto;

import com.example.minhm80.domain.PaymentType;
import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Customer;
import com.example.minhm80.modal.OrderItem;
import com.example.minhm80.modal.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Double totalAmount;

    private LocalDateTime createdAt;

private Long branchId;
private Long customerId;

    private BranchDTO branch;


    private UserDto cashier;


    private Customer customer;

    private List<OrderItemDTO> items;

    private PaymentType paymentType;


}
