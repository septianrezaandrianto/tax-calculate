package com.project.taxcalculate.service;

import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.repository.TaxRateMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculatorServiceImpl implements TaxCalculatorService {

    @Autowired
    private TaxRateMasterRepository taxRateMasterRepository;

    @Override
    public GeneralResponse<Object> calculateTax(double annualIncome) {
        return null;
    }

}
