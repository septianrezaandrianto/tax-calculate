package com.project.taxcalculate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "TaxReliefMasterDetail")
@Table(name = "tax_relief_master_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxReliefMasterDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tax_relief_master_id", nullable = false)
    private TaxReliefMaster taxReliefMaster;
    @Column(columnDefinition = "TEXT")
    private String individualReliefType;
    private BigDecimal amount;
    private String information;

}
