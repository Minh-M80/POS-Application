package com.example.minhm80.modal;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Shift start is required")
    private LocalDateTime shiftStart;

//    @NotNull(message = "Shift end is required")
    private LocalDateTime shiftEnd;

//    @NotNull(message = "Total sales is required")
//    @PositiveOrZero(message = "Total sales must be greater than or equal to 0")
    private Double totalSales;

//    @NotNull(message = "Total refunds is required")
//    @PositiveOrZero(message = "Total refunds must be greater than or equal to 0")
    private Double totalRefunds;

//    @NotNull(message = "Net sale is required")
//    @PositiveOrZero(message = "Net sale must be greater than or equal to 0")
    private Double netSale;

    @PositiveOrZero(message = "Total orders must be greater than or equal to 0")
    private int totalOrders;

    @ManyToOne
//    @NotNull(message = "Cashier is required")
    private User cashier;

    @ManyToOne
//    @NotNull(message = "Branch is required")
    private Branch branch;

    @Transient
//    @Valid
    private List<PaymentSumary> paymentSumaries;


    @Transient
    private List<Product> topSellingProducts;

    @Transient
    private List<Order> recentOrders;

    @OneToMany(mappedBy = "shiftReport",cascade = CascadeType.ALL)
//    @Valid
    private List<Refund> refunds;


}
