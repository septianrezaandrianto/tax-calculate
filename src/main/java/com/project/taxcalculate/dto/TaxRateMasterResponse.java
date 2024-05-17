package com.project.taxcalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxRateMasterResponse {

    private Long id;
    private String category;
    private BigDecimal chargeableIncomeMin;
    private BigDecimal chargeableIncomeMax;
    private BigDecimal calculationMin;
    private BigDecimal caluclationMax;
    private Double rate;
    private BigDecimal taxMin;
    private BigDecimal taxMax;
}
