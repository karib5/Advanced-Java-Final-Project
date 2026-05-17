package com.eliteperformance.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BonusRecord {
    private Long bonusId;
    private Long employeeId;
    private Integer reviewYear;
    private BigDecimal totalKpiScore;
    private String category;
    private BigDecimal bonusPercentage;
    private BigDecimal bonusAmount;
    private BigDecimal totalCompensation;
    private LocalDateTime createdAt;
}