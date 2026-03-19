package com.example.minhm80.service.impl;

import com.example.minhm80.domain.PaymentType;
import com.example.minhm80.mapper.ShiftReportMapper;
import com.example.minhm80.modal.*;
import com.example.minhm80.payload.dto.ShiftReportDTO;
import com.example.minhm80.repository.BranchRepository;
import com.example.minhm80.repository.OrderRepository;
import com.example.minhm80.repository.RefundRepository;
import com.example.minhm80.repository.ShiftReportRepository;
import com.example.minhm80.service.ShiftReportService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ShiftReportServiceImpl implements ShiftReportService {


    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final BranchRepository branchRepository;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;


    @Override
    public ShiftReportDTO startShift(Long cashierId,
                                     Long branchId,
                                     LocalDateTime shiftStart) throws Exception {

        User currentUser = userService.getCurrentUser();
        shiftStart = LocalDateTime.now();

        LocalDateTime startOfDate = shiftStart.withHour(0).withMinute(0).withSecond(0);

        LocalDateTime endOfDate = shiftStart.withHour(23).withMinute(59).withSecond(59);

        Optional<ShiftReport> existing = shiftReportRepository.findByCashierAndShiftStartBetween(currentUser,startOfDate,endOfDate);

        if(existing.isPresent()){
            throw new Exception("Shift already started today");
        }

        Branch branch = currentUser.getBranch();

        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(branch)

                .build();

     ShiftReport saveReport =shiftReportRepository.save(shiftReport);



        return ShiftReportMapper.toDTO(saveReport);
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId,
                                   LocalDateTime shiftEnd) throws Exception {
        User currentUser = userService.getCurrentUser();

        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser).orElseThrow(
                () -> new Exception("Shift not found")
        );

        shiftReport.setShiftEnd(shiftEnd);

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
                currentUser.getId(),
                shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );

        double totalRefunds = refunds.stream().mapToDouble(refund -> refund.getAmount() != null?
                refund.getAmount():0.0).sum();


        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(
                currentUser,shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );

        double totalSales = orders.stream().mapToDouble(
                order ->order.getTotalAmount() != null ? order.getTotalAmount():0.0
        ).sum();




        int totalOrders = orders.size();

        double netSales = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSale(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSumaries(getPaymentSummaries(orders,totalSales));
        shiftReport.setRefunds(refunds);


        ShiftReport saveReport = shiftReportRepository.save(shiftReport);


        return ShiftReportMapper.toDTO(saveReport);
    }



    @Override
    public ShiftReportDTO getShiftReportById(Long id) {
        return null;
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }


    private List<PaymentSumary> getPaymentSummaries(List<Order> orders,
                                                    double totalSales) {
        // CASH - order 1 (amount = 1000) , order => 2000

        // card - order 3 => 3000
        // upi - order 4(amount = 500),order 5(amount = 500) => 1500

        // cash = 30%
        // card = 50%
        // upi = 20%


        Map<PaymentType, List<Order>> grouped = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getPaymentType()!=null?
                        order.getPaymentType():PaymentType.CASH));

        List<PaymentSumary> sumaries = new ArrayList<>();

        for (Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()){
                double amount = entry.getValue().stream()
                        .mapToDouble(Order::getTotalAmount).sum();

            int transactions = entry.getValue().size();
            double percent = (amount/totalSales)*100;


            PaymentSumary ps = new PaymentSumary();
            ps.setType(entry.getKey());
            ps.setTotalAmout(amount);
            ps.setTransactionCount(transactions);
            ps.setPercentage(percent);
            sumaries.add(ps);



        }
        return  sumaries;




    }

    private List<Product> getTopSellingProducts(List<Order> orders) {
        Map<Product,Integer> productSalesMap = new HashMap<>();



        for (Order order: orders){
            for(OrderItem item:order.getItems()){
                Product product = item.getProduct();
                productSalesMap.put(product,
                        productSalesMap.getOrDefault(product,0) + item.getQuantity());
            }
        }

        return productSalesMap.entrySet().stream()
                .sorted( (a,b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }


}
