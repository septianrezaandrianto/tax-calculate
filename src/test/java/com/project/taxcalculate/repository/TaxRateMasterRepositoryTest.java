package com.project.taxcalculate.repository;

import com.project.taxcalculate.entity.TaxRateMaster;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TaxRateMasterRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaxRateMasterRepository taxRateMasterRepository;

    @Test
    void testGetDataByPage() {
        TaxRateMaster taxRateMasterA = createTaxRateMaster("A", BigDecimal.ZERO, BigDecimal.valueOf(5000),
                BigDecimal.ZERO, BigDecimal.valueOf(5000), 0, BigDecimal.ZERO, BigDecimal.ZERO);
        TaxRateMaster taxRateMasterB = createTaxRateMaster("B", BigDecimal.valueOf(5001), BigDecimal.valueOf(20000),
                BigDecimal.valueOf(5000), BigDecimal.valueOf(15000), 1, BigDecimal.ZERO, BigDecimal.valueOf(150));
        entityManager.persist(taxRateMasterA);
        entityManager.persist(taxRateMasterB);
        entityManager.flush();
        Pageable pageable = PageRequest.of(0, 10);
        Page<TaxRateMaster> page = taxRateMasterRepository.getDataByPage("%", pageable);
        assertEquals(2, page.getTotalElements());
    }

    private TaxRateMaster createTaxRateMaster(String category, BigDecimal chargeableIncomeMin, BigDecimal chargeableIncomeMax,
                                              BigDecimal calculationMin, BigDecimal calculationMax, double rate,
                                              BigDecimal taxMin, BigDecimal taxMax) {
        TaxRateMaster taxRateMaster = new TaxRateMaster();
        taxRateMaster.setCategory(category);
        taxRateMaster.setChargeableIncomeMin(chargeableIncomeMin);
        taxRateMaster.setChargeableIncomeMax(chargeableIncomeMax);
        taxRateMaster.setCalculationMin(calculationMin);
        taxRateMaster.setCaluclationMax(calculationMax);
        taxRateMaster.setRate(rate);
        taxRateMaster.setTaxMin(taxMin);
        taxRateMaster.setTaxMax(taxMax);
        taxRateMaster.setIsDelete(false);
        return taxRateMaster;
    }
}
