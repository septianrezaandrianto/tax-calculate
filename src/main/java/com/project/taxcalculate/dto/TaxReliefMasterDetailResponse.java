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
public class TaxReliefMasterDetailResponse {

    private Long id;
    private String individualReliefType;
    private String amount;
    private String information;
}
