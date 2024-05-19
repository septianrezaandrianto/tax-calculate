package com.project.taxcalculate.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculateTaxRequest {

    @Pattern(regexp = "\\d+", message = "Annual income format isn't valid")
    @NotBlank(message = "Annual income can't be empty")
    @NotNull(message = "Annual income can't be empty")
    @Min(0)
    private String annualIncome;
}
