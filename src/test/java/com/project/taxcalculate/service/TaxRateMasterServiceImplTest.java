package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.Constant;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
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
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaxRateMasterServiceImplTest {

    @Mock
    private TaxRateMasterRepository taxRateMasterRepository;
    @Mock private GeneralUtil generalUtil;

    @InjectMocks
    private TaxRateMasterServiceImpl taxRateMasterService;

    @Test
    void testInsertMasterData() {
        when(taxRateMasterRepository.saveAll(anyList())).thenReturn(mappingTaxRateMasterList());
        taxRateMasterService.insertMasterData();
    }

    @Test
    void testGetDataByPage_withFilter() {
        PaginatePageRequest request = new PaginatePageRequest();
        request.setFilter("A");
        request.setPageNumber("0");
        request.setPageSize("10");

        Page<TaxRateMaster> mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(mappingTaxRateMasterList());
        when(mockPage.getTotalElements()).thenReturn((long)mappingTaxRateMasterList().size());
        when(taxRateMasterRepository.getDataByPageWithFilter(anyString(), any(Pageable.class)))
                .thenReturn(mockPage);
        when(generalUtil.mappingFilter(any(String.class))).thenReturn("%A%");
        when(generalUtil.convertToRM(BigDecimal.valueOf(5000))).thenReturn("RM5,000");
        GeneralResponse<Object> response = taxRateMasterService.getDataByPage(request);
        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals(Constant.ResponseApi.SUCCESS_MESSAGE, response.getResponseMessage());
        assertEquals(mappingTaxRateMasterResponseList(), response.getData());
        assertEquals(0, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertEquals(1, response.getTotalData());
    }

    @Test
    void testGetDataByPage() {
        PaginatePageRequest request = new PaginatePageRequest();
        request.setPageNumber("0");
        request.setPageSize("10");

        Page<TaxRateMaster> mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(mappingTaxRateMasterList());
        when(mockPage.getTotalElements()).thenReturn((long)mappingTaxRateMasterList().size());
        when(taxRateMasterRepository.getDataByPage(any(Pageable.class)))
                .thenReturn(mockPage);

        GeneralResponse<Object> response = taxRateMasterService.getDataByPage(request);
        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals(Constant.ResponseApi.SUCCESS_MESSAGE, response.getResponseMessage());
        assertEquals(mappingTaxRateMasterResponseList(), response.getData());
        assertEquals(0, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertEquals(1, response.getTotalData());
    }

    private List<TaxRateMasterResponse> mappingTaxRateMasterResponseList() {
        List<TaxRateMasterResponse> taxRateMasterList = new ArrayList<>();
        TaxRateMasterResponse taxRateMasterResponse = TaxRateMasterResponse.builder()
                .id(1L)
                .category("A")
                .chargeableIncomeMin(generalUtil.convertToRM(BigDecimal.valueOf(600001)))
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
                .id(1L)
                .category("A")
                .chargeableIncomeMin(BigDecimal.ONE)
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
