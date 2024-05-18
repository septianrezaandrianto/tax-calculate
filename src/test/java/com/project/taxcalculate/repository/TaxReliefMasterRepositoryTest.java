package com.project.taxcalculate.repository;

import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.entity.TaxReliefMaster;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TaxReliefMasterRepositoryTest {
    @Autowired
    private TaxReliefMasterRepository taxReliefMasterRepository;

    @Test
    public void testGetDataByPage() {
        TaxReliefMaster taxReliefMaster =  TaxReliefMaster.builder()
                .id(1L)
                .number("1")
                .createdDate(new Date())
                .createdBy(Constant.DEFAULT_SYSTEM)
                .modifiedDate(new Date())
                .modifiedBy(Constant.DEFAULT_SYSTEM)
                .isDelete(false)
                .build();
        taxReliefMasterRepository.save(taxReliefMaster);

        Pageable pageable = PageRequest.of(0, 10);
        Page<TaxReliefMaster> page = taxReliefMasterRepository.getDataByPage(pageable);
        assertEquals(1, page.getTotalElements());
    }


}
