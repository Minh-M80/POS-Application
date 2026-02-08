package com.example.minhm80.modal;

import com.example.minhm80.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @OneToOne
    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    @Embedded
    private StoreContact contact = new StoreContact();

    @PrePersist
    // chạy trước khi insert
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        status = StoreStatus.PENDING;
    }

    @PreUpdate
    // chạy trước khi update
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }


}
