package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.TaxCalculateConstant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.dto.TaxRateMasterResponse;
import com.project.taxcalculate.entity.TaxRateMaster;
import com.project.taxcalculate.repository.TaxRateMasterRepository;
import com.project.taxcalculate.util.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TaxRateMasterServiceImpl implements TaxRateMasterService {

    @Autowired
    private TaxRateMasterRepository taxRateMasterRepository;
    @Autowired
    private GeneralUtil generalUtil;

    @Override
    public void insertMasterData() {
        log.info("Insert Master Data Start");
        List<TaxRateMaster> resultList = new ArrayList<>();
        TaxRateMaster taxRateMasterA = mappingTaxMasterData("A", BigDecimal.ZERO, BigDecimal.valueOf(5000), BigDecimal.ZERO,
                BigDecimal.valueOf(5000), 0, BigDecimal.ZERO ,BigDecimal.ZERO);
        TaxRateMaster taxRateMasterB = mappingTaxMasterData("B", BigDecimal.valueOf(5001), BigDecimal.valueOf(20000), BigDecimal.valueOf(5000),
                BigDecimal.valueOf(15000), 1, BigDecimal.ZERO, BigDecimal.valueOf(150));
        TaxRateMaster taxRateMasterC = mappingTaxMasterData("C", BigDecimal.valueOf(20001), BigDecimal.valueOf(35000), BigDecimal.valueOf(20000),
                BigDecimal.valueOf(35000), 3, BigDecimal.valueOf(150), BigDecimal.valueOf(450));
        TaxRateMaster taxRateMasterD = mappingTaxMasterData("D", BigDecimal.valueOf(35001), BigDecimal.valueOf(50000), BigDecimal.valueOf(35000),
                BigDecimal.valueOf(50000), 6, BigDecimal.valueOf(600), BigDecimal.valueOf(900));
        TaxRateMaster taxRateMasterE = mappingTaxMasterData("E", BigDecimal.valueOf(50001), BigDecimal.valueOf(70000), BigDecimal.valueOf(50000),
                BigDecimal.valueOf(70000), 11, BigDecimal.valueOf(1500), BigDecimal.valueOf(2200));
        TaxRateMaster taxRateMasterF = mappingTaxMasterData("F", BigDecimal.valueOf(70001), BigDecimal.valueOf(100000), BigDecimal.valueOf(70000),
                BigDecimal.valueOf(100000), 19, BigDecimal.valueOf(3700), BigDecimal.valueOf(5700));
        TaxRateMaster taxRateMasterG = mappingTaxMasterData("G", BigDecimal.valueOf(100001), BigDecimal.valueOf(400000), BigDecimal.valueOf(100000),
                BigDecimal.valueOf(400000), 25, BigDecimal.valueOf(9400), BigDecimal.valueOf(75000));
        TaxRateMaster taxRateMasterH = mappingTaxMasterData("H", BigDecimal.valueOf(400001), BigDecimal.valueOf(600000), BigDecimal.valueOf(400000),
                BigDecimal.valueOf(600000), 26, BigDecimal.valueOf(84400), BigDecimal.valueOf(52000));
        TaxRateMaster taxRateMasterI = mappingTaxMasterData("I", BigDecimal.valueOf(600001), BigDecimal.valueOf(2000000), BigDecimal.valueOf(600000),
                BigDecimal.valueOf(2000000), 28, BigDecimal.valueOf(136400), BigDecimal.valueOf(392000));
        TaxRateMaster taxRateMasterJ = mappingTaxMasterData("J", BigDecimal.valueOf(2000001), null, BigDecimal.valueOf(2000000),
                null, 30, BigDecimal.valueOf(528400), null);

        resultList.add(taxRateMasterA);
        resultList.add(taxRateMasterB);
        resultList.add(taxRateMasterC);
        resultList.add(taxRateMasterD);
        resultList.add(taxRateMasterE);
        resultList.add(taxRateMasterF);
        resultList.add(taxRateMasterG);
        resultList.add(taxRateMasterH);
        resultList.add(taxRateMasterI);
        resultList.add(taxRateMasterJ);

        taxRateMasterRepository.saveAll(resultList);
        log.info("Insert Master Data Finnish");
    }

    @Override
    public GeneralResponse<Object> getDataByPage(PaginatePageRequest paginatePageRequest) {
        String filter = mappingFilter(paginatePageRequest.getFilter());
        int pageSize = Integer.parseInt(paginatePageRequest.getPageSize());
        int pageNumber = Integer.parseInt(paginatePageRequest.getPageNumber());
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<TaxRateMaster> taxRateMasterPage = taxRateMasterRepository.getDataByPage(filter,paging);

        List<TaxRateMasterResponse> taxRateMasterResponseList = new ArrayList<>();
        if (!taxRateMasterPage.getContent().isEmpty()) {
            for (TaxRateMaster trm : taxRateMasterPage.getContent()) {
                TaxRateMasterResponse trmr = new TaxRateMasterResponse(trm.getId(),
                        trm.getCategory(),
                        generalUtil.convertToRM(trm.getChargeableIncomeMin()),
                        generalUtil.convertToRM(trm.getChargeableIncomeMax()),
                        generalUtil.convertToRM(trm.getCalculationMin()),
                        generalUtil.convertToRM(trm.getCaluclationMax()),
                        trm.getRate(),
                        generalUtil.convertToRM(trm.getTaxMin()),
                        generalUtil.convertToRM(trm.getTaxMax())
                );
                taxRateMasterResponseList.add(trmr);
            }
        }

        return GeneralResponse.builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(TaxCalculateConstant.ResponseApi.SUCCESS_MESSAGE)
                .data(taxRateMasterResponseList)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalData(taxRateMasterPage.getTotalElements())
                .build();
    }

    private String mappingFilter(String filter) {
        if(Objects.isNull(filter) || "".equals(filter.trim())) {
            return "%%";
        } else {
            return "%".concat(filter.toLowerCase()).concat("%");
        }
    }

    private TaxRateMaster mappingTaxMasterData(String category, BigDecimal chargeableIncomeMin, BigDecimal chargeableIncomeMax,
                                               BigDecimal calculationMin, BigDecimal caluclationMax, double rate, BigDecimal taxMin, BigDecimal taxMax) {
        Date nowDate = new Date();
        String inputer = "SYSTEM";

        return TaxRateMaster.builder()
                .category(category)
                .chargeableIncomeMin(chargeableIncomeMin)
                .chargeableIncomeMax(chargeableIncomeMax)
                .calculationMin(calculationMin)
                .caluclationMax(caluclationMax)
                .rate(rate)
                .taxMin(taxMin)
                .taxMax(taxMax)
                .createdDate(nowDate)
                .createdBy(inputer)
                .modifiedDate(nowDate)
                .modifiedBy(inputer)
                .isDelete(false)
                .build();
    }
}
