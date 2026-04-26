package com.example.minhm80.modal;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Category name is required")
//    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    private String name;


    @ManyToOne
//    @NotNull(message = "Store is required")
//    @JoinColumn(name = "store_id")
    private Store store;


}
