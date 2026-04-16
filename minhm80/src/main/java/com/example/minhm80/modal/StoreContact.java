package com.example.minhm80.modal;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreContact {
//    @NotBlank(message = "Address is required")
    @Size(max = 255)
    private String address;

//    @NotBlank(message = "Phone is required")
//    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Invalid phone number")
    private String phone;

//    @NotBlank(message = "Email is required")
    @Email(message = "invalid format")
    private String email;
}
