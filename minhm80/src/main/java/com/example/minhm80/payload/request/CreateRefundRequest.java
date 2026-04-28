package com.example.minhm80.payload.request;

import com.example.minhm80.domain.PaymentType;
import lombok.Data;

@Data
public class CreateRefundRequest {
    private Long orderId;
    private String reason;
    private Double amount;
    private PaymentType paymentType;
}
