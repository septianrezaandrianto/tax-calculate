package com.project.taxcalculate.controller;

import com.google.gson.Gson;
import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.service.TaxReliefMasterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaxReliefMasterController.class)
public class TaxReliefMasterControllerTest {

    @MockBean
    private TaxReliefMasterServiceImpl taxReliefMasterService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDataByPage() throws Exception {
        PaginatePageRequest request = new PaginatePageRequest();
        request.setFilter("");
        request.setPageNumber("0");
        request.setPageSize("10");

        when(taxReliefMasterService.getDataByPage(request))
                .thenReturn(mappingGeneralResponse());

        MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/taxReliefMaster/getDataByPage")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .content(new Gson().toJson(request)))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

    private GeneralResponse<Object> mappingGeneralResponse() {
        return GeneralResponse.builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(Constant.ResponseApi.SUCCESS_MESSAGE)
                .build();
    }

}
