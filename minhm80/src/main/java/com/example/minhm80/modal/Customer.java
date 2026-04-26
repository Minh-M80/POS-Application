package com.example.minhm80.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Full name is required")
//    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

//    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
//    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

//    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Phone number is invalid")
    private String phone;


    private LocalDateTime createdDate;

    private LocalDateTime updatedAt;
}
