package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.TaxCalculateConstant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.dto.TaxRateMasterResponse;
import com.project.taxcalculate.entity.TaxRateMaster;
import com.project.taxcalculate.repository.TaxRateMasterRepository;
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

    @Override
    public void insertMasterData() {
        log.info("Insert Master Data Start");
        List<TaxRateMaster> resultList = new ArrayList<>();
        TaxRateMaster taxRateMasterA = mappingTaxMasterData("A", BigDecimal.ZERO, BigDecimal.valueOf(5000), BigDecimal.ZERO,
                BigDecimal.valueOf(5000), 0, BigDecimal.ZERO ,BigDecimal.ZERO);
        TaxRateMaster taxRateMasterB = mappingTaxMasterData("B", BigDecimal.valueOf(5001), BigDecimal.valueOf(20000), BigDecimal.valueOf(5000),
                BigDecimal.valueOf(15000), 1, BigDecimal.ZERO, BigDecimal.valueOf(150));

        resultList.add(taxRateMasterA);
        resultList.add(taxRateMasterB);
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
                        trm.getChargeableIncomeMin(),
                        trm.getChargeableIncomeMax(),
                        trm.getCalculationMin(),
                        trm.getCaluclationMax(),
                        trm.getRate(),
                        trm.getTaxMin(),
                        trm.getTaxMax()
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
