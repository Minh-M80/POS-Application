package com.example.minhm80.modal;

import com.example.minhm80.domain.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @NotNull(message = "Order is required")
    private Order order;


//    @NotBlank(message = "Reason is required")
//    @Size(min = 5, max = 255, message = "Reason must be between 5 and 255 characters")
    private String reason;

//    @NotNull(message = "Amount is required")
//    @Positive(message = "Refund amount must be greater than 0")
    private Double amount;

    @ManyToOne
    @JsonIgnore
    private ShiftReport shiftReport;

    @ManyToOne
//    @NotNull(message = "Cashier is required")
    private User cashier;


    @ManyToOne
//    @NotNull(message = "Branch is required")
    private Branch branch;



//    @NotNull(message = "Payment type is required")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }
    private LocalDateTime createdAt;









}
