package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.TaxReliefMasterDetailResponse;
import com.project.taxcalculate.dto.TaxReliefMasterResponse;
import com.project.taxcalculate.entity.TaxRateMaster;
import com.project.taxcalculate.entity.TaxReliefMasterDetail;
import com.project.taxcalculate.handler.NotFoundException;
import com.project.taxcalculate.repository.TaxRateMasterRepository;
import com.project.taxcalculate.repository.TaxReliefMasterDetailRepository;
import com.project.taxcalculate.util.GeneralUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaxCalculatorServiceImplTest {

    @Mock
    private TaxRateMasterRepository taxRateMasterRepository;
    @Mock
    private TaxReliefMasterDetailRepository taxReliefMasterDetailRepository;
    @Mock
    private GeneralUtil generalUtil;
    @InjectMocks
    private TaxCalculatorServiceImpl taxCalculatorService;

    @Test
    void testCalculateTax_Success() {
        String annualIncome = "50000";
        BigDecimal income = BigDecimal.valueOf(Double.parseDouble(annualIncome));
        when(taxRateMasterRepository.getTaxRateMasterByIncomeMin(income)).thenReturn(mappingTaxRateMaster());
        when(taxReliefMasterDetailRepository.getTaxReliefMasterDetailListByAmount(BigDecimal.valueOf(20001))).thenReturn(mappingTaxReliefMasterDetailList());
        when(generalUtil.convertToRM(any(BigDecimal.class))).thenReturn("RM1000");

        GeneralResponse<Object> response = taxCalculatorService.calculateTax(annualIncome);

        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals(Constant.MESSAGE.SUCCESS_MESSAGE, response.getResponseMessage());
        assertNotNull(response.getData());
    }

    @Test
    void testCalculateTax_NotFound() {
        String annualIncome = "50000";
        when(taxRateMasterRepository.getTaxRateMasterByIncomeMin(any(BigDecimal.class))).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> taxCalculatorService.calculateTax(annualIncome));
        assertEquals(Constant.MESSAGE.DATA_NOT_FOUND_MESSAGE.replace("{value}", annualIncome), exception.getMessage());
    }

    private TaxRateMaster mappingTaxRateMaster() {
        Date nowDate = new Date();
        String inputer = "SYSTEM";

        return TaxRateMaster.builder()
                .id(1L)
                .category("A")
                .chargeableIncomeMin(BigDecimal.ONE)
                .calculationMin(BigDecimal.ONE)
                .caluclationMax(BigDecimal.TWO)
                .rate(1)
                .taxMin(BigDecimal.ZERO)
                .taxMax(BigDecimal.TEN)
                .createdDate(nowDate)
                .createdBy(inputer)
                .modifiedDate(nowDate)
                .modifiedBy(inputer)
                .isDelete(false)
                .build();
    }

    private List<TaxReliefMasterDetail> mappingTaxReliefMasterDetailList() {
        List<TaxReliefMasterDetail> result = new ArrayList<>();
        TaxReliefMasterDetail trmd = TaxReliefMasterDetail.builder()
                .id(1L)
                .individualReliefType("Type 1")
                .amount(BigDecimal.valueOf(10000))
                .information(Constant.RESTRICTED_INFORMATION)
                .build();
        result.add(trmd);
        return result;
    }
}
