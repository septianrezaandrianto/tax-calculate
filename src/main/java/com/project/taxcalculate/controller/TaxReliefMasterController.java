package com.project.taxcalculate.controller;

import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.service.TaxReliefMasterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxReliefMaster")
public class TaxReliefMasterController {

    @Autowired
    private TaxReliefMasterService taxReliefMasterService;

    @PostMapping("/getDataByPage")
    public ResponseEntity<GeneralResponse<Object>> getDataByPage(@Valid @RequestBody PaginatePageRequest paginatePageRequest) {
        return ResponseEntity.ok(taxReliefMasterService.getDataByPage(paginatePageRequest));
    }
}
