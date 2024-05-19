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
    private String chargeableIncomeMin;
    private String calculationMin;
    private String caluclationMax;
    private Double rate;
    private String taxMin;
    private String taxMax;
}
