package com.project.taxcalculate.service;

import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;

public interface TaxReliefMasterService {

    void insertMasterData();
    GeneralResponse<Object> getDataByPage(PaginatePageRequest paginatePageRequest);
}
