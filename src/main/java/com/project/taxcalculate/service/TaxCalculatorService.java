package com.project.taxcalculate.service;

import com.project.taxcalculate.dto.GeneralResponse;

public interface TaxCalculatorService {

    GeneralResponse<Object> calculateTax(String annualIncome);
}
