package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.TaxCalculateConstant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.dto.TaxRateMasterResponse;
import com.project.taxcalculate.entity.TaxRateMaster;
import com.project.taxcalculate.repository.TaxRateMasterRepository;
import com.project.taxcalculate.util.GeneralUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaxRateMasterServiceImplTest {

    @Mock
    private TaxRateMasterRepository taxRateMasterRepository;
    @Mock private GeneralUtil generalUtil;

    @InjectMocks
    private TaxRateMasterServiceImpl taxRateMasterService;

    @Test
    void testInsertMasterData() {
        when(taxRateMasterRepository.saveAll(anyList())).thenReturn(new ArrayList<>());
        taxRateMasterService.insertMasterData();
    }

    @Test
    void testGetDataByPage() {
        PaginatePageRequest request = new PaginatePageRequest();
        request.setFilter("A");
        request.setPageNumber("0");
        request.setPageSize("10");

        Page<TaxRateMaster> mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(mappingTaxRateMasterList());
        when(mockPage.getTotalElements()).thenReturn(0L);
        when(taxRateMasterRepository.getDataByPage(anyString(), Mockito.any(Pageable.class)))
                .thenReturn(mockPage);

        GeneralResponse<Object> response = taxRateMasterService.getDataByPage(request);
        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals(TaxCalculateConstant.ResponseApi.SUCCESS_MESSAGE, response.getResponseMessage());
        assertEquals(mappingTaxRateMasterResponseList(), response.getData());
        assertEquals(0, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertEquals(0, response.getTotalData());
    }

    @Test
    void testGetDataByPage_emptyList() {
        Page<TaxRateMaster> mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(new ArrayList<>());
        when(mockPage.getTotalElements()).thenReturn(0L);
        when(taxRateMasterRepository.getDataByPage(anyString(), Mockito.any(Pageable.class)))
                .thenReturn(mockPage);
        PaginatePageRequest request = new PaginatePageRequest();
        request.setFilter("");
        request.setPageNumber("0");
        request.setPageSize("10");

        GeneralResponse<Object> response = taxRateMasterService.getDataByPage(request);
        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals(TaxCalculateConstant.ResponseApi.SUCCESS_MESSAGE, response.getResponseMessage());
        assertEquals(new ArrayList<>(), response.getData());
        assertEquals(0, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertEquals(0, response.getTotalData());
    }

    private List<TaxRateMasterResponse> mappingTaxRateMasterResponseList() {
        List<TaxRateMasterResponse> taxRateMasterList = new ArrayList<>();
        TaxRateMasterResponse taxRateMasterResponse = TaxRateMasterResponse.builder()
                .category("A")
                .chargeableIncomeMin(generalUtil.convertToRM(BigDecimal.valueOf(600001)))
                .chargeableIncomeMax(generalUtil.convertToRM(BigDecimal.valueOf(2000000)))
                .calculationMin(generalUtil.convertToRM(BigDecimal.valueOf(600000)))
                .caluclationMax(generalUtil.convertToRM(BigDecimal.valueOf(392000)))
                .rate(1.0)
                .taxMin(generalUtil.convertToRM(BigDecimal.valueOf(2000000)))
                .taxMax(generalUtil.convertToRM(BigDecimal.valueOf(136400)))
                .build();
        taxRateMasterList.add(taxRateMasterResponse);
        return taxRateMasterList;
    }

    private List<TaxRateMaster> mappingTaxRateMasterList() {
        Date nowDate = new Date();
        String inputer = "SYSTEM";

        List<TaxRateMaster> taxRateMasterList = new ArrayList<>();
        TaxRateMaster taxRateMaster = TaxRateMaster.builder()
                .category("A")
                .chargeableIncomeMin(BigDecimal.ONE)
                .chargeableIncomeMax(BigDecimal.TEN)
                .calculationMin(BigDecimal.ONE)
                .caluclationMax(BigDecimal.TWO)
                .rate(1.0)
                .taxMin(BigDecimal.ZERO)
                .taxMax(BigDecimal.TEN)
                .createdDate(nowDate)
                .createdBy(inputer)
                .modifiedDate(nowDate)
                .modifiedBy(inputer)
                .isDelete(false)
                .build();
        taxRateMasterList.add(taxRateMaster);
        return taxRateMasterList;
    }



}
