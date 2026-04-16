package com.example.minhm80.modal;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

//    @NotBlank(message = "Branch name is required")
//    @Size(min = 2, max = 100, message = "Branch name must be between 2 and 100 characters")
    private String name;

//    @NotBlank(message = "Address is required")
//    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

//    @NotBlank(message = "Phone is required")
//    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Invalid phone number")
    private String phone;

//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
    private String  email;

    @ElementCollection
//    @NotEmpty(message = "Working days must not be empty")
//    @Valid
    private List<String> workingDays;

//    @NotNull(message = "Open time is required")
    private LocalDateTime openTime;

//    @NotNull(message = "Close time is required")
    private LocalDateTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @NotNull(message = "Store is required")
    private Store store;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;

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
