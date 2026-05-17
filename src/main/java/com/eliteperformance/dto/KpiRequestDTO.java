package com.eliteperformance.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class KpiRequestDTO {
    @NotNull
    private Long employeeId;

    @NotNull
    private Integer reviewYear;

    @NotNull @DecimalMin("0") @DecimalMax("25")
    private Double taskCompletion;

    @NotNull @DecimalMin("0") @DecimalMax("15")
    private Double attendance;

    @NotNull @DecimalMin("0") @DecimalMax("15")
    private Double teamCollaboration;

    @NotNull @DecimalMin("0") @DecimalMax("15")
    private Double problemSolving;

    @NotNull @DecimalMin("0") @DecimalMax("10")
    private Double communication;

    @NotNull @DecimalMin("0") @DecimalMax("10")
    private Double leadership;

    @NotNull @DecimalMin("0") @DecimalMax("10")
    private Double clientSatisfaction;
}