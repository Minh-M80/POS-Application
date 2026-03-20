package com.example.minhm80.service.impl;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.RefundMapper;
import com.example.minhm80.modal.Branch;
import com.example.minhm80.modal.Order;
import com.example.minhm80.modal.Refund;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.RefundDTO;
import com.example.minhm80.repository.OrderRepository;
import com.example.minhm80.repository.RefundRepository;
import com.example.minhm80.service.RefundService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;

    @Override
    public RefundDTO createRefund(RefundDTO refund) throws Exception {
        User cashier = userService.getCurrentUser();

        Order order = orderRepository.findById(refund.getOrderId()).orElseThrow(
                () -> new UserException("order not found", HttpStatus.NOT_FOUND)
        );

        Branch branch = order.getBranch();


        Refund createdRefund = Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();

        Refund savedRefund = refundRepository.save(createdRefund);


        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDTO> getAllRefunds() throws Exception {
        return refundRepository.findAll().stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception {
         return refundRepository.findByCashierId(cashierId).stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception {
        return refundRepository.findByShiftReportId(shiftReportId).stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashierIdAndCreatedAtBetween(
                cashierId,startDate,endDate
        ).stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream()
                .map(
                        RefundMapper::toDTO
                ).collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long refundId) throws Exception {
        return refundRepository.findById(refundId).map(
                RefundMapper::toDTO
        ).orElseThrow(
                () -> new UserException("refund not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
            this.getRefundById(refundId);
            refundRepository.deleteById(refundId);
    }
}
