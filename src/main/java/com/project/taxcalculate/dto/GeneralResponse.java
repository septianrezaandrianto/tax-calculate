package com.project.taxcalculate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> {

    private int responseCode;
    private String responseMessage;
    private T data;
    private List<String> errorList;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalData;

}
