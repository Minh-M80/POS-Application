package com.example.minhm80.modal;

import com.example.minhm80.domain.OrderStatus;
import com.example.minhm80.domain.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Total amount is required")
//    @PositiveOrZero(message = "Total amount must be greater than or equal to 0")
    private Double totalAmount;

    private LocalDateTime createdAt;

    @ManyToOne
//    @NotNull(message = "Branch is required")
    private Branch branch;

    @ManyToOne
//    @NotNull(message = "Cashier is required")
    private User cashier;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
//    @NotEmpty(message = "Order items must not be empty")
//    @Valid
    private List<OrderItem> items;

//    @NotNull(message = "Payment type is required")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = OrderStatus.PENDING;
        }

    }






}
