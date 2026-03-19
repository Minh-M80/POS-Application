package com.example.minhm80.modal;

import com.example.minhm80.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSumary {
    private PaymentType type;

    private Double totalAmout;
    private int transactionCount;

    private double percentage;


}
