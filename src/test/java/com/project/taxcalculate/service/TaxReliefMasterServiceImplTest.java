package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.entity.TaxReliefMaster;
import com.project.taxcalculate.entity.TaxReliefMasterDetail;
import com.project.taxcalculate.repository.TaxReliefMasterDetailRepository;
import com.project.taxcalculate.repository.TaxReliefMasterRepository;
import com.project.taxcalculate.util.GeneralUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class TaxReliefMasterServiceImplTest {

    @Mock
    private TaxReliefMasterRepository taxReliefMasterRepository;
    @Mock
    private TaxReliefMasterDetailRepository taxReliefMasterDetailRepository;
    @Mock
    private GeneralUtil generalUtil;

    @InjectMocks
    private TaxReliefMasterServiceImpl taxReliefMasterService;

    @Test
    void testInsertMasterData() {
        when(taxReliefMasterRepository.saveAll(anyList())).thenReturn(mappingTaxReliefMasterList());
        when(taxReliefMasterDetailRepository.saveAll(anyList())).thenReturn(mappingTaxReliefMasterDetailList());
        taxReliefMasterService.insertMasterData();
    }

    @Test
    void testgetDataByPage() {
        PaginatePageRequest request = new PaginatePageRequest();
        request.setPageNumber("0");
        request.setPageSize("10");

        Page<TaxReliefMaster> mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(mappingTaxReliefMasterList());
        when(mockPage.getTotalElements()).thenReturn((long)mappingTaxReliefMasterList().size());
        when(taxReliefMasterRepository.getDataByPage(any(Pageable.class))).thenReturn(mockPage);
        when(taxReliefMasterDetailRepository.getDataListByParentId(anyLong())).thenReturn(mappingTaxReliefMasterDetailList());
        when(generalUtil.mappingFilter(any(String.class))).thenReturn("%A%");
        when(generalUtil.convertToRM(BigDecimal.valueOf(5000))).thenReturn("RM5,000");
        GeneralResponse<Object> response = taxReliefMasterService.getDataByPage(request);
        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals(Constant.MESSAGE.SUCCESS_MESSAGE, response.getResponseMessage());
        assertEquals(0, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertEquals(1, response.getTotalData());
    }
    private List<TaxReliefMaster> mappingTaxReliefMasterList() {
        List<TaxReliefMaster> result = new ArrayList<>();
        result.add(mappingTaxReliefMaster());
        return result;
    }

    private TaxReliefMaster mappingTaxReliefMaster() {
        Date nowDate = new Date();
        String inputer = "SYSTEM";
        return TaxReliefMaster.builder()
                .id(1L)
                .number("1")
                .createdDate(nowDate)
                .createdBy(inputer)
                .modifiedDate(nowDate)
                .modifiedBy(inputer)
                .isDelete(false)
                .build();
    }

    private List<TaxReliefMasterDetail> mappingTaxReliefMasterDetailList() {
        List<TaxReliefMasterDetail> result = new ArrayList<>();
        result.add(mappingTaxReliefMasterDetail());
        return result;
    }

    private TaxReliefMasterDetail mappingTaxReliefMasterDetail() {
        return TaxReliefMasterDetail.builder()
                .id(1L)
                .taxReliefMaster(mappingTaxReliefMaster())
                .individualReliefType("Individual and dependent relatives")
                .amount(BigDecimal.valueOf(9000))
                .information(null)
                .build();
    }
}
