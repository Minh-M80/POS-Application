package com.example.minhm80.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Quantity is required")
//    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

//    @NotNull(message = "Price is required")
//    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    private Double price;

    @ManyToOne
//    @NotNull(message = "Product is required")
    private Product product;

    @ManyToOne
//    @NotNull(message = "Order is required")
    private Order order;



}
