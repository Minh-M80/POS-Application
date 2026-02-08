package com.example.minhm80.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String  email;

    @ElementCollection
    private List<String> workingDays;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Store store;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;

    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    // chạy trước khi update
    protected void onUpdate(){

        updatedAt = LocalDateTime.now();
    }

}
