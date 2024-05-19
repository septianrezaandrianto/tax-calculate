package com.project.taxcalculate.repository;

import com.project.taxcalculate.entity.TaxRateMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRateMasterRepository extends JpaRepository<TaxRateMaster, Long> {

    @Query(value = "select * from tax_rate_master trm where  lower(trm.category) like ?1 and trm.is_delete = false " +
            "order by trm.category ASC", nativeQuery = true)
    Page<TaxRateMaster> getDataByPageWithFilter(String filter, Pageable pageable);

    @Query(value = "select * from tax_rate_master trm where trm.is_delete = false order by trm.category ASC", nativeQuery = true)
    Page<TaxRateMaster> getDataByPage(Pageable pageable);
}
