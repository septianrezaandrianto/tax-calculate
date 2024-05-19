package com.project.taxcalculate.repository;

import com.project.taxcalculate.entity.TaxReliefMasterDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TaxReliefMasterDetailRepository extends JpaRepository<TaxReliefMasterDetail, Long> {

    @Query(value = "select * from tax_relief_master_detail trmd where trmd.tax_relief_master_id = ?1 order by trmd.id ASC",
            nativeQuery = true)
    List<TaxReliefMasterDetail> getDataListByParentId(Long id);

    @Query(value = "select * from tax_relief_master_detail trmd where trmd.amount <= ?1 order by trmd.id ASC", nativeQuery = true)
    List<TaxReliefMasterDetail> getTaxReliefMasterDetailListByAmount(BigDecimal amount);

    @Query(value = "select * from tax_relief_master_detail trmd where trmd.id = ?1 order by trmd.id ASC",
            nativeQuery = true)
    TaxReliefMasterDetail getDataById(Long id);
}
