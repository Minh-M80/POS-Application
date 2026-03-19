package com.example.minhm80.modal;

import com.example.minhm80.domain.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Order order;


    private String reason;

    private Double amount;

    @ManyToOne
    @JsonIgnore
    private ShiftReport shiftReport;

    @ManyToOne
    private User cashier;


    @ManyToOne
    private Branch branch;



    private PaymentType paymentType;


    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }
    private LocalDateTime createdAt;









}
