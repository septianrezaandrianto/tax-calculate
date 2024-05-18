package com.project.taxcalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxReliefMasterResponse {

    private Long id;
    private String number;
    private List<TaxReliefMasterDetailResponse> detailList;
}
