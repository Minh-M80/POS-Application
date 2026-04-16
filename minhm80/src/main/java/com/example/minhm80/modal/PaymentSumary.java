package com.example.minhm80.modal;

import com.example.minhm80.domain.PaymentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PaymentSumary {
//    @NotNull(message = "Payment type is required")
    private PaymentType type;

//    @NotNull(message = "Total amount is required")
//    @PositiveOrZero(message = "Total amount must be greater than or equal to 0")
    private Double totalAmout;

//    @PositiveOrZero(message = "Transaction count must be greater than or equal to 0")
    private int transactionCount;

//    @DecimalMin(value = "0.0", message = "Percentage must be greater than or equal to 0")
    private double percentage;


}
