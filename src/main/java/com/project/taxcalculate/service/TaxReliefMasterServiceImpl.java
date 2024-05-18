package com.project.taxcalculate.service;

import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.dto.TaxReliefMasterDetailResponse;
import com.project.taxcalculate.dto.TaxReliefMasterResponse;
import com.project.taxcalculate.entity.TaxReliefMaster;
import com.project.taxcalculate.entity.TaxReliefMasterDetail;
import com.project.taxcalculate.repository.TaxReliefMasterDetailRepository;
import com.project.taxcalculate.repository.TaxReliefMasterRepository;
import com.project.taxcalculate.util.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TaxReliefMasterServiceImpl implements TaxReliefMasterService {

    @Autowired
    private TaxReliefMasterRepository taxReliefMasterRepository;
    @Autowired
    private TaxReliefMasterDetailRepository taxReliefMasterDetailRepository;
    @Autowired
    private GeneralUtil generalUtil;

    @Override
    public void insertMasterData() {
        log.info("Insert Master Data Start");
        List<TaxReliefMaster> taxReliefMasterList = new ArrayList<>();
        List<TaxReliefMasterDetail> taxReliefMasterDetailList = new ArrayList<>();

        TaxReliefMaster taxReliefMaster1 = mappingTaxReliefMaster("1");
        taxReliefMasterList.add(taxReliefMaster1);
        TaxReliefMasterDetail taxReliefMasterDetail1 = mappingTaxReliefMasterDetail(taxReliefMaster1, "Individual and dependent relatives",
                BigDecimal.valueOf(9000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail1);

        TaxReliefMaster taxReliefMaster2 = mappingTaxReliefMaster("2");
        taxReliefMasterList.add(taxReliefMaster2);
        TaxReliefMasterDetail taxReliefMasterDetail2 = mappingTaxReliefMasterDetail(taxReliefMaster2,"Medical treatment, special needs and carer expenses for parents " +
                "(Medical condition certified by medical practitioner)", BigDecimal.valueOf(8000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail2);

        TaxReliefMaster taxReliefMaster3 = mappingTaxReliefMaster("3");
        taxReliefMasterList.add(taxReliefMaster3);
        TaxReliefMasterDetail taxReliefMasterDetail3 = mappingTaxReliefMasterDetail(taxReliefMaster3,"Purchase of basic supporting equipment for disabled self, " +
                "spouse, child or parent", BigDecimal.valueOf(6000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail3);

        TaxReliefMaster taxReliefMaster4 = mappingTaxReliefMaster("4");
        taxReliefMasterList.add(taxReliefMaster4);
        TaxReliefMasterDetail taxReliefMasterDetail4 = mappingTaxReliefMasterDetail(taxReliefMaster4,"Disabled individual",
                BigDecimal.valueOf(6000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail4);

        TaxReliefMaster taxReliefMaster5 = mappingTaxReliefMaster("5");
        taxReliefMasterList.add(taxReliefMaster5);
        TaxReliefMasterDetail taxReliefMasterDetail5 = mappingTaxReliefMasterDetail(taxReliefMaster5, "Education fees (Self):\n" +
                        "\n" +
                        "    Other than a degree at masters or doctorate level – Course of study in law, accounting, islamic financing, tehcnical, vocational, industrial, scientific or technology\n" +
                        "    Degree at masters or doctorate level – Any course of study\n" +
                        "    Course of study undertaken for the purpose of upskilling or self-enhancement (Restricted to RM2,000)",
                BigDecimal.valueOf(7000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail5);

        TaxReliefMaster taxReliefMaster6 = mappingTaxReliefMaster("6");
        taxReliefMasterList.add(taxReliefMaster6);

        TaxReliefMasterDetail taxReliefMasterDetail6 = mappingTaxReliefMasterDetail(taxReliefMaster6, "Medical expenses on:\n" +
                "\n" +
                "    Serious diseases for self, spouse or child\n" +
                "    Fertility treatment for self or spouse\n" +
                "    Vaccination for self, spouse and child (Restricted to RM1,000)", BigDecimal.valueOf(10000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail6);

        TaxReliefMaster taxReliefMaster7 = mappingTaxReliefMaster("7");
        taxReliefMasterList.add(taxReliefMaster7);
        TaxReliefMasterDetail taxReliefMasterDetail7 = mappingTaxReliefMasterDetail(taxReliefMaster7, "Expenses (Restricted to RM1,000) on:\n" +
        "\n" +
        "    Complete medical examination for self, spouse or child\n" +
        "    COVID-19 detection test including purchase of self-detection test kit for self, spouse or child\n" +
        "    Mental health examination or consultation for self, spouse or child", BigDecimal.valueOf(10000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail7);

        TaxReliefMaster taxReliefMaster8 = mappingTaxReliefMaster("8");
        taxReliefMasterList.add(taxReliefMaster8);
        TaxReliefMasterDetail taxReliefMasterDetail8 = mappingTaxReliefMasterDetail(taxReliefMaster8, "Expenses (Restricted to RM4,000) for child aged 18 and below:\n" +
                "\n" +
                "    Assessment of intellectual disability diagnosis\n" +
                "    Early intervention programme / intellectual disability rehabilitation treatment", BigDecimal.valueOf(10000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail8);

        TaxReliefMaster taxReliefMaster9 = mappingTaxReliefMaster("9");
        taxReliefMasterList.add(taxReliefMaster9);
        TaxReliefMasterDetail taxReliefMasterDetail9 = mappingTaxReliefMasterDetail(taxReliefMaster9, "Lifestyle – Expenses for the use / benefit of self, spouse or child in respect of:\n" +
                "\n" +
                "    Purchase or subscription of books / journals / magazines / newspapers / other similar publications (Not banned reading materials)\n" +
                "    Purchase of personal computer, smartphone or tablet (Not for business use)\n" +
                "    Purchase of sports equipment for sports activity defined under the Sports Development Act 1997 and payment of gym membership\n" +
                "    Payment of monthly bill for internet subscription (Under own name)", BigDecimal.valueOf(2500), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail9);

        TaxReliefMaster taxReliefMaster10 = mappingTaxReliefMaster("10");
        taxReliefMasterList.add(taxReliefMaster10);
        TaxReliefMasterDetail taxReliefMasterDetail10 = mappingTaxReliefMasterDetail(taxReliefMaster10,"Lifestyle – Additional relief for the use / benefit of self, spouse or child in respect of:\n" +
                        "\n" +
                        "    Purchase of sports equipment for any sports activity as defined under the Sports Development Act 1997\n" +
                        "    Payment of rental or entrance fee to any sports facility\n" +
                        "    Payment of registration fee for any sports competition where the organizer is approved and licensed by the Commissioner of Sports under the Sports Development Act 1997",
                BigDecimal.valueOf(500), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail10);

        TaxReliefMaster taxReliefMaster11 = mappingTaxReliefMaster("11");
        taxReliefMasterList.add(taxReliefMaster11);
        TaxReliefMasterDetail taxReliefMasterDetail11 = mappingTaxReliefMasterDetail(taxReliefMaster11, "Purchase of breastfeeding equipment for own use " +
                        "for a child aged 2 years and below (Deduction allowed once in every TWO (2) years of assessment)",
                BigDecimal.valueOf(1000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail11);

        TaxReliefMaster taxReliefMaster12 = mappingTaxReliefMaster("12");
        taxReliefMasterList.add(taxReliefMaster12);
        TaxReliefMasterDetail taxReliefMasterDetail12 = mappingTaxReliefMasterDetail(taxReliefMaster12, "Child care " +
                        "fees to a registered child care centre / kindergarten for a child aged 6 years and below",
                BigDecimal.valueOf(3000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail12);

        TaxReliefMaster taxReliefMaster13 = mappingTaxReliefMaster("13");
        taxReliefMasterList.add(taxReliefMaster13);
        TaxReliefMasterDetail taxReliefMasterDetail13 = mappingTaxReliefMasterDetail(taxReliefMaster13, "Net deposit in " +
                        "Skim Simpanan Pendidikan Nasional (Net deposit is the total deposit in 2023 MINUS total withdrawal in 2023)",
                BigDecimal.valueOf(8000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail13);

        TaxReliefMaster taxReliefMaster14 = mappingTaxReliefMaster("14");
        taxReliefMasterList.add(taxReliefMaster14);
        TaxReliefMasterDetail taxReliefMasterDetail14 = mappingTaxReliefMasterDetail(taxReliefMaster14, "Husband / wife / payment " +
                "of alimony to former wife", BigDecimal.valueOf(4000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail14);

        TaxReliefMaster taxReliefMaster15 = mappingTaxReliefMaster("15");
        taxReliefMasterList.add(taxReliefMaster15);
        TaxReliefMasterDetail taxReliefMasterDetail15 = mappingTaxReliefMasterDetail(taxReliefMaster15,"Disabled husband / wife",
                BigDecimal.valueOf(5000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail15);

        TaxReliefMaster taxReliefMaster16a = mappingTaxReliefMaster("16a");
        taxReliefMasterList.add(taxReliefMaster16a);
        TaxReliefMasterDetail taxReliefMasterDetail16a = mappingTaxReliefMasterDetail(taxReliefMaster16a,
                "Each unmarried child and under the age of 18 years old", BigDecimal.valueOf(2000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail16a);

        TaxReliefMaster taxReliefMaster16b = mappingTaxReliefMaster("16b");
        taxReliefMasterList.add(taxReliefMaster16b);
        TaxReliefMasterDetail taxReliefMasterDetail16b1 = mappingTaxReliefMasterDetail(taxReliefMaster16b,
                "Each unmarried child of 18 years and above who is receiving full-time education (\"A-Level\", certificate, " +
                        "matriculation or preparatory courses).", BigDecimal.valueOf(2000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail16b1);
        TaxReliefMasterDetail taxReliefMasterDetail16b2 = mappingTaxReliefMasterDetail(taxReliefMaster16b,
                "Each unmarried child of 18 years and above that:\n" +
                        "\n" +
                        "    receiving further education in Malaysia in respect of an award of diploma or higher (excluding matriculation/ preparatory courses).\n" +
                        "    receiving further education outside Malaysia in respect of an award of degree or its equivalent (including Master or Doctorate).\n" +
                        "    the instruction and educational establishment shall be approved by the relevant government authority.\n", BigDecimal.valueOf(8000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail16b2);

        TaxReliefMaster taxReliefMaster16c = mappingTaxReliefMaster("16c");
        taxReliefMasterList.add(taxReliefMaster16c);
        TaxReliefMasterDetail taxReliefMasterDetail16c1 = mappingTaxReliefMasterDetail(taxReliefMaster16c,
                "Disabled child", BigDecimal.valueOf(6000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail16c1);
        TaxReliefMasterDetail taxReliefMasterDetail16c2 = mappingTaxReliefMasterDetail(taxReliefMaster16c,
                "Additional exemption of RM8,000 disable child age 18 years old and above, not married and pursuing " +
                        "diplomas or above qualification in Malaysia @ bachelor degree or above outside Malaysia in program " +
                        "and in Higher Education Institute that is accredited by related Government authorities", BigDecimal.valueOf(8000), null);
        taxReliefMasterDetailList.add(taxReliefMasterDetail16c2);

        TaxReliefMaster taxReliefMaster17 = mappingTaxReliefMaster("17");
        taxReliefMasterList.add(taxReliefMaster17);
        TaxReliefMasterDetail taxReliefMasterDetail17 = mappingTaxReliefMasterDetail(taxReliefMaster17,
                "Life insurance and EPF\n" +
                        "\n" +
                        "Civil servants’ pension schemes, non-civil servants pension schemes and self-employed category:\n" +
                        "\n" +
                        "    Mandatory contributions to approved schemes or voluntary contributions to EPF (excluding private " +
                        "retirement schemes) or contributions under any written law (Restricted to RM4,000)\n" +
                        "    Life insurance premium payments or family takaful contributions or additional voluntary contributions " +
                        "to EPF (Restricted to RM3,000)\n", BigDecimal.valueOf(7000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail17);

        TaxReliefMaster taxReliefMaster18 = mappingTaxReliefMaster("18");
        taxReliefMasterList.add(taxReliefMaster18);
        TaxReliefMasterDetail taxReliefMasterDetail18 = mappingTaxReliefMasterDetail(taxReliefMaster18,
                "Deferred Annuity and Private Retirement Scheme (PRS)", BigDecimal.valueOf(3000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail18);

        TaxReliefMaster taxReliefMaster19 = mappingTaxReliefMaster("19");
        taxReliefMasterList.add(taxReliefMaster19);
        TaxReliefMasterDetail taxReliefMasterDetail19 = mappingTaxReliefMasterDetail(taxReliefMaster19,
                "Education and medical insurance", BigDecimal.valueOf(3000), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail19);

        TaxReliefMaster taxReliefMaster20 = mappingTaxReliefMaster("20");
        taxReliefMasterList.add(taxReliefMaster20);
        TaxReliefMasterDetail taxReliefMasterDetail20 = mappingTaxReliefMasterDetail(taxReliefMaster20,
                "Contribution to the Social Security Organization (SOCSO)", BigDecimal.valueOf(350), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail20);

        TaxReliefMaster taxReliefMaster21 = mappingTaxReliefMaster("21");
        taxReliefMasterList.add(taxReliefMaster21);
        TaxReliefMasterDetail taxReliefMasterDetail21 = mappingTaxReliefMasterDetail(taxReliefMaster21,
                "Expenses on charging facilities for Electric Vehicle (Not for business use)", BigDecimal.valueOf(2500), Constant.RESTRICTED_INFORMATION);
        taxReliefMasterDetailList.add(taxReliefMasterDetail21);

        taxReliefMasterRepository.saveAll(taxReliefMasterList);
        taxReliefMasterDetailRepository.saveAll(taxReliefMasterDetailList);
        log.info("Insert Master Data Finish");
    }

    @Override
    public GeneralResponse<Object> getDataByPage(PaginatePageRequest paginatePageRequest) {
        int pageSize = Integer.parseInt(paginatePageRequest.getPageSize());
        int pageNumber = Integer.parseInt(paginatePageRequest.getPageNumber());
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<TaxReliefMaster> taxReliefMasterPage = taxReliefMasterRepository.getDataByPage(paging);

        List<TaxReliefMasterResponse> taxReliefMasterResponseList = new ArrayList<>();
        if (!taxReliefMasterPage.getContent().isEmpty()) {
            for (TaxReliefMaster trm : taxReliefMasterPage.getContent()) {
                List<TaxReliefMasterDetail> taxReliefMasterDetailList = taxReliefMasterDetailRepository.getDataListByParentId(trm.getId());
                List<TaxReliefMasterDetailResponse> taxReliefMasterDetailResponseList = mappingDetailList(taxReliefMasterDetailList);

                TaxReliefMasterResponse taxReliefMasterResponse = TaxReliefMasterResponse.builder()
                        .id(trm.getId())
                        .number(trm.getNumber())
                        .detailList(taxReliefMasterDetailResponseList)
                        .build();
                taxReliefMasterResponseList.add(taxReliefMasterResponse);
            }
        }

        return GeneralResponse.builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(Constant.ResponseApi.SUCCESS_MESSAGE)
                .data(taxReliefMasterResponseList)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalData(taxReliefMasterPage.getTotalElements())
                .build();
    }

    private List<TaxReliefMasterDetailResponse> mappingDetailList(List<TaxReliefMasterDetail> taxReliefMasterDetailListList) {
        List<TaxReliefMasterDetailResponse> result = new ArrayList<>();
        for(TaxReliefMasterDetail trmd : taxReliefMasterDetailListList) {
            TaxReliefMasterDetailResponse taxReliefMasterDetailResponse = TaxReliefMasterDetailResponse.builder()
                    .id(trmd.getId())
                    .individualReliefType(trmd.getIndividualReliefType())
                    .amount(generalUtil.convertToRM(trmd.getAmount()))
                    .information(trmd.getInformation())
                    .build();
            result.add(taxReliefMasterDetailResponse);
        }

        return result;
    }

    private TaxReliefMaster mappingTaxReliefMaster(String number) {
        Date nowDate = new Date();
        return TaxReliefMaster.builder()
                .number(number)
                .createdDate(nowDate)
                .createdBy(Constant.DEFAULT_SYSTEM)
                .modifiedDate(nowDate)
                .modifiedBy(Constant.DEFAULT_SYSTEM)
                .isDelete(false)
                .build();
    }

    private TaxReliefMasterDetail mappingTaxReliefMasterDetail(TaxReliefMaster taxReliefMaster, String type, BigDecimal amount, String information) {
        return TaxReliefMasterDetail.builder()
                .taxReliefMaster(taxReliefMaster)
                .individualReliefType(type)
                .amount(amount)
                .information(information)
                .build();
    }
}
