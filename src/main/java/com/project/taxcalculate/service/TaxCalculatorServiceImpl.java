package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.TaxReliefMasterDetailResponse;
import com.project.taxcalculate.dto.TaxReliefMasterResponse;
import com.project.taxcalculate.entity.TaxRateMaster;
import com.project.taxcalculate.entity.TaxReliefMaster;
import com.project.taxcalculate.entity.TaxReliefMasterDetail;
import com.project.taxcalculate.handler.NotFoundException;
import com.project.taxcalculate.repository.TaxRateMasterRepository;
import com.project.taxcalculate.repository.TaxReliefMasterDetailRepository;
import com.project.taxcalculate.repository.TaxReliefMasterRepository;
import com.project.taxcalculate.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TaxCalculatorServiceImpl implements TaxCalculatorService {

    @Autowired
    private TaxRateMasterRepository taxRateMasterRepository;
    @Autowired
    private TaxReliefMasterDetailRepository taxReliefMasterDetailRepository;
    @Autowired
    private GeneralUtil generalUtil;

    @Override
    public GeneralResponse<Object> calculateTax(String annualIncome) {
        BigDecimal income = BigDecimal.valueOf(Double.parseDouble(annualIncome));
        TaxRateMaster taxRateMaster = taxRateMasterRepository.getTaxRateMasterByIncomeMin(income);
        if (Objects.isNull(taxRateMaster)) {
            throw new NotFoundException(Constant.MESSAGE.DATA_NOT_FOUND_MESSAGE.replace("{value}", annualIncome));
        }

        BigDecimal tax = BigDecimal.ZERO;
        if (Objects.isNull(taxRateMaster.getCaluclationMax())) {
            tax = taxRateMaster.getTaxMin();
        } else {
            tax = income.subtract(taxRateMaster.getCaluclationMax()).compareTo(BigDecimal.ZERO) == 0
                    ? taxRateMaster.getTaxMax() : taxRateMaster.getTaxMin();
        }
        BigDecimal taxDeduction = income.multiply(BigDecimal.valueOf(taxRateMaster.getRate())).divide(BigDecimal.valueOf(100));
        BigDecimal takeHomePay = income.subtract(tax).subtract(taxDeduction);

        List<TaxReliefMasterResponse> taxReliefMasterResponseList = new ArrayList<>();
        List<TaxReliefMasterDetail> taxReliefMasterDetailList = taxReliefMasterDetailRepository.getTaxReliefMasterDetailListByAmount(takeHomePay);
        for (TaxReliefMasterDetail trmd : taxReliefMasterDetailList) {

            List<TaxReliefMasterDetailResponse> taxReliefMasterDetailResponseList = mappingDetailList(trmd.getId());
            TaxReliefMasterResponse taxReliefMasterResponse = TaxReliefMasterResponse.builder()
                    .id(trmd.getTaxReliefMaster().getId())
                    .number(trmd.getTaxReliefMaster().getNumber())
                    .detailList(taxReliefMasterDetailResponseList)
                    .build();
            taxReliefMasterResponseList.add(taxReliefMasterResponse);
        }
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("annualIncome", generalUtil.convertToRM(income));
        mapResult.put("taxDeduction", generalUtil.convertToRM(taxDeduction));
        mapResult.put("tax" , generalUtil.convertToRM(tax));
        mapResult.put("rate", taxRateMaster.getRate() + " %");
        mapResult.put("takeHomePay" , generalUtil.convertToRM(takeHomePay));
        mapResult.put("taxClaimOptions" , taxReliefMasterResponseList);
        return GeneralResponse.builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(Constant.MESSAGE.SUCCESS_MESSAGE)
                .data(mapResult)
                .build();
    }

    private List<TaxReliefMasterDetailResponse> mappingDetailList(long id) {
        List<TaxReliefMasterDetailResponse> result = new ArrayList<>();
        TaxReliefMasterDetail trmd = taxReliefMasterDetailRepository.getDataById(id);
        TaxReliefMasterDetailResponse taxReliefMasterDetailResponse = TaxReliefMasterDetailResponse.builder()
                .id(trmd.getId())
                .individualReliefType(trmd.getIndividualReliefType())
                .amount(generalUtil.convertToRM(trmd.getAmount()))
                .information(trmd.getInformation())
                .build();
        result.add(taxReliefMasterDetailResponse);
        return result;
    }

}
