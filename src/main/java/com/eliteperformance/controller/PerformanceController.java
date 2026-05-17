package com.eliteperformance.controller;

import com.eliteperformance.dto.BonusResponseDTO;
import com.eliteperformance.dto.KpiRequestDTO;
import com.eliteperformance.model.BonusRecord;
import com.eliteperformance.repository.BonusRecordRepository;
import com.eliteperformance.repository.EmployeeRepository;
import com.eliteperformance.service.BonusService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PerformanceController {

    private final BonusService bonusService;
    private final BonusRecordRepository bonusRecordRepository;
    private final EmployeeRepository employeeRepository;

    public PerformanceController(BonusService bonusService,
                                 BonusRecordRepository bonusRecordRepository,
                                 EmployeeRepository employeeRepository) {
        this.bonusService = bonusService;
        this.bonusRecordRepository = bonusRecordRepository;
        this.employeeRepository = employeeRepository;
    }

    // MANAGER or ADMIN - calculate bonus
    @PostMapping("/performances/calculate")
    public ResponseEntity<BonusResponseDTO> calculateBonus(@Valid @RequestBody KpiRequestDTO request) {
        BonusResponseDTO response = bonusService.calculateBonus(request);
        return ResponseEntity.ok(response);
    }

    // EMPLOYEE - view own bonus records
    @GetMapping("/bonus/my")
    public ResponseEntity<List<BonusRecord>> getMyBonuses(Authentication authentication) {
        String username = authentication.getName();
        return employeeRepository.findByUsername(username)
                .map(emp -> ResponseEntity.ok(bonusRecordRepository.findByEmployeeId(emp.getEmployeeId())))
                .orElse(ResponseEntity.notFound().build());
    }

    // ADMIN - view all bonus records
    @GetMapping("/bonus/all")
    public ResponseEntity<List<BonusRecord>> getAllBonuses() {
        return ResponseEntity.ok(bonusRecordRepository.findAll());
    }
}