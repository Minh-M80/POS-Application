package com.example.minhm80.payload.dto;

import com.example.minhm80.modal.*;
import com.example.minhm80.payload.dto.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ShiftReportDTO {
    private Long id;

    private LocalDateTime shiftStart;

    private LocalDateTime shiftEnd;

    private Double totalSales;

    private Double totalRefunds;

    private Double netSale;

    private Integer totalOrders;


    private UserDto cashier;
    private Long cashierId;


    private BranchDTO branch;
    private Long branchId;


    private List<PaymentSumary> paymentSumaries;



    private List<ProductDTO> topSellingProducts;


    private List<OrderDTO> recentOrders;


    private List<RefundDTO> refunds;
}
