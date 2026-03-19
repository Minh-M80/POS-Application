package com.example.minhm80.mapper;

import com.example.minhm80.modal.Order;
import com.example.minhm80.modal.Product;
import com.example.minhm80.modal.Refund;
import com.example.minhm80.modal.ShiftReport;
import com.example.minhm80.payload.dto.OrderDTO;
import com.example.minhm80.payload.dto.ProductDTO;
import com.example.minhm80.payload.dto.RefundDTO;
import com.example.minhm80.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDTO toDTO(ShiftReport shiftReport){
        return ShiftReportDTO.builder()
                .id(shiftReport.getId())
                .shiftEnd(shiftReport.getShiftEnd())
                .shiftStart(shiftReport.getShiftStart())
                .totalSales(shiftReport.getTotalSales())
                .totalOrders(shiftReport.getTotalOrders())
                .netSale(shiftReport.getNetSale())
                .totalRefunds(shiftReport.getTotalRefunds())
                .cashier(UserMapper.toDTO(shiftReport.getCashier()))
                .cashierId(shiftReport.getCashier().getId())
                .branchId(shiftReport.getBranch().getId())
                .recentOrders(mapOrders(shiftReport.getRecentOrders()))
                .topSellingProducts(mapProducts(shiftReport.getTopSellingProducts()))
                .refunds(mapRefunds(shiftReport.getRefunds()))
                .paymentSumaries(shiftReport.getPaymentSumaries())
                .build();
    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if(refunds == null || refunds.isEmpty()) {return null;}

        return refunds.stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if(topSellingProducts == null || topSellingProducts.isEmpty()) {return null;}

        return topSellingProducts.stream().map(
                ProductMapper::toDTO
        ).collect(Collectors.toList());
    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrders) {
        if(recentOrders == null || recentOrders.isEmpty()) {return null;}

        return recentOrders.stream().map(
                OrderMapper::toDTO
        ).collect(Collectors.toList());
    }
}
