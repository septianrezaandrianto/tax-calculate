package com.project.taxcalculate.repository;

import com.project.taxcalculate.entity.TaxReliefMasterDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TaxReliefMasterDetailRepositoryTest {

    @Autowired
    private TaxReliefMasterDetailRepository taxReliefMasterDetailRepository;

    @Test
    public void testGetDataListByParentId() {
        List<TaxReliefMasterDetail> result = taxReliefMasterDetailRepository.getDataListByParentId(1L);
        assertNotNull(result);
    }

    @Test
    public void testGetTaxReliefMasterDetailListByAmount() {
        List<TaxReliefMasterDetail> result = taxReliefMasterDetailRepository.getTaxReliefMasterDetailListByAmount(BigDecimal.TEN);
        assertNotNull(result);
    }
}
