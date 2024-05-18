package com.project.taxcalculate.repository;

import com.project.taxcalculate.entity.TaxReliefMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxReliefMasterRepository extends JpaRepository<TaxReliefMaster, Long> {

    @Query(value = "select * from tax_relief_master trm where trm.is_delete = false order by trm.id ASC",
            nativeQuery = true)
    Page<TaxReliefMaster> getDataByPage(Pageable pageable);
}
