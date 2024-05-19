package com.project.taxcalculate.controller;

import com.project.taxcalculate.dto.CalculateTaxRequest;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.service.TaxCalculatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax")
public class TaxCalculatorController {

    @Autowired
    private TaxCalculatorService taxCalculatorService;

    @PostMapping("/calculateTax")
    public ResponseEntity<GeneralResponse<Object>> calculateTax(@Valid @RequestBody CalculateTaxRequest calculateTaxRequest) {
        return ResponseEntity.ok(taxCalculatorService.calculateTax(calculateTaxRequest.getAnnualIncome()));
    }
}
