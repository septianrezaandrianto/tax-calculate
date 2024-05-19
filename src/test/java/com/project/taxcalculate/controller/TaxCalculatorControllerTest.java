package com.project.taxcalculate.controller;

import com.google.gson.Gson;
import com.project.taxcalculate.constant.Constant;
import com.project.taxcalculate.dto.CalculateTaxRequest;
import com.project.taxcalculate.dto.GeneralResponse;
import com.project.taxcalculate.dto.PaginatePageRequest;
import com.project.taxcalculate.service.TaxCalculatorServiceImpl;
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
@WebMvcTest(TaxCalculatorController.class)
public class TaxCalculatorControllerTest {

    @MockBean
    private TaxCalculatorServiceImpl taxCalculatorService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculateTax() throws Exception {
        CalculateTaxRequest calculateTaxRequest = CalculateTaxRequest.builder()
                .annualIncome("10000")
                .build();

        when(taxCalculatorService.calculateTax(calculateTaxRequest.getAnnualIncome()))
                .thenReturn(mappingGeneralResponse());

        MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/tax/calculateTax")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .content(new Gson().toJson(calculateTaxRequest)))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

    private GeneralResponse<Object> mappingGeneralResponse() {
        return GeneralResponse.builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(Constant.MESSAGE.SUCCESS_MESSAGE)
                .build();
    }
}
