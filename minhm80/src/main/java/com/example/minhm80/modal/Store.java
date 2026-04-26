package com.example.minhm80.modal;

import java.time.LocalDateTime;

import com.example.minhm80.domain.StoreStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
//    @NotBlank(message = "Brand is required")
//    @Size(min = 2, max = 100)
    private String brand;

    @OneToOne
//    @NotNull(message = "Store admin is required")
    private User storeAdmin;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Size(max = 500)
    private String description;

//    @NotBlank(message = "Store type is required")
//    @Size(max = 50)
    private String storeType;

//    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Embedded
//    @NotNull(message = "Contact is required")
//    @Valid
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
