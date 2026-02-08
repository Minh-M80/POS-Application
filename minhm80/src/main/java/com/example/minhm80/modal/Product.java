package com.example.minhm80.modal;

import com.example.minhm80.domain.StoreStatus;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String sku;//Stock Keeping Unit
    //Mã định danh duy nhất cho từng sản phẩm trong kho

    private String description;

    private Double mrp;//Maximum Retail Price
    //Giá bán lẻ tối đa được phép bán cho người tiêu dùng

    private Double sellingPrice;

    private String brand;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Store store;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    // chạy trước khi update
    protected void onUpdate(){

        updatedAt = LocalDateTime.now();
    }
}
