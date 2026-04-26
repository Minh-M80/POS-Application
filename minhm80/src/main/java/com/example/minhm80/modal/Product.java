package com.example.minhm80.modal;

import com.example.minhm80.domain.StoreStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
//    @NotBlank(message = "Product name is required")
//    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String name;

    @Column(nullable = false,unique = true)
//    @NotBlank(message = "SKU is required")
//    @Size(min = 3, max = 50, message = "SKU must be between 3 and 50 characters")
//    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "SKU can only contain letters, numbers, _ and -")
    private String sku;//Stock Keeping Unit
    //Mã định danh duy nhất cho từng sản phẩm trong kho

//    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

//    @NotNull(message = "MRP is required")
//    @PositiveOrZero(message = "MRP must be greater than or equal to 0")
    private Double mrp;//Maximum Retail Price
    //Giá bán lẻ tối đa được phép bán cho người tiêu dùng

//    @NotNull(message = "Selling price is required")
//    @PositiveOrZero(message = "Selling price must be greater than or equal to 0")
    private Double sellingPrice;

//    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

//    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String image;

    @ManyToOne
//    @NotNull(message = "Category is required")
    private Category category;

    @ManyToOne
    @NotNull(message = "Store is required")
    private Store store;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    // chạy trước khi update
    protected void onUpdate(){

        updatedAt = LocalDateTime.now();
    }
}
