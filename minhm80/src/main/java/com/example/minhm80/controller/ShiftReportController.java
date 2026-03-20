package com.example.minhm80.controller;

import com.example.minhm80.payload.dto.ShiftReportDTO;
import com.example.minhm80.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {

    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDTO> startShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.startShift()
        );
    }

    @PatchMapping ("/end")
    public ResponseEntity<ShiftReportDTO> endShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.endShift()
        );
    }

    @GetMapping("/current")
    public ResponseEntity<ShiftReportDTO> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getCurrentShiftProgress()
        );
    }


}
