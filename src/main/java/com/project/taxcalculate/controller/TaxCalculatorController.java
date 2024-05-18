package com.project.taxcalculate.controller;

import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.service.TaxCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
public class TaxCalculatorController {

    @Autowired
    private TaxCalculatorService taxCalculatorService;

    @GetMapping("/calculateTax")
    public ResponseEntity<GeneralResponse<Object>> calculateTax(@RequestParam(name = "annualIncome") double annualIncome) {
        return ResponseEntity.ok(taxCalculatorService.calculateTax(annualIncome));
    }
}
