package com.example.minhm80.modal;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
//    @NotNull(message = "Branch is required")
    private Branch branch;

    @ManyToOne
//    @NotNull(message = "Product is required")
    private Product product;

    @Column(nullable = false)
//    @NotNull(message = "Quantity is required")
//    @PositiveOrZero(message = "Quantity must be greater than or equal to 0")
    private Integer quantity;


    private LocalDateTime lastUpdate;


    @PrePersist
    @PreUpdate
    protected void onUpdate(){
        lastUpdate = LocalDateTime.now();
    }





}
