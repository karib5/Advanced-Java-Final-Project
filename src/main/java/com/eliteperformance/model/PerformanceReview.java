package com.eliteperformance.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PerformanceReview {
    private Long reviewId;
    private Long employeeId;
    private Integer reviewYear;
    private BigDecimal taskCompletion;
    private BigDecimal attendance;
    private BigDecimal teamCollaboration;
    private BigDecimal problemSolving;
    private BigDecimal communication;
    private BigDecimal leadership;
    private BigDecimal clientSatisfaction;
    private BigDecimal totalKpiScore;
    private LocalDateTime createdAt;
}