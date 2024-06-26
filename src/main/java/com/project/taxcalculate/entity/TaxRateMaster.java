package com.project.taxcalculate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "TaxRateMaster")
@Table(name = "tax_rate_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxRateMaster implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private BigDecimal chargeableIncomeMin;
    private BigDecimal calculationMin;
    private BigDecimal caluclationMax;
    private Integer rate;
    private BigDecimal taxMin;
    private BigDecimal taxMax;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private Boolean isDelete;

}
