package com.example.minhm80.payload.dto;

import com.example.minhm80.domain.PaymentType;
import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Order;
import com.example.minhm80.modal.ShiftReport;
import com.example.minhm80.modal.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDTO {
    private Long id;


    private OrderDTO order;

    private Long orderId;


    private String reason;

    private Double amount;


//    private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDto cashier;
private String cashierName;


    private BranchDTO branch;

private Long branchId;

    private PaymentType paymentType;


    private LocalDateTime createdAt;
}
