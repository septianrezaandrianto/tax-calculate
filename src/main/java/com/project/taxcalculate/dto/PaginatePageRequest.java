package com.project.taxcalculate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginatePageRequest {

    private String filter;
    @NotBlank(message = "Page number can't be empty")
    @NotNull(message = "Page number can't be empty")
    @Pattern(regexp = "\\d+", message = "Page number format isn't valid")
    private String pageNumber;
    @NotBlank(message = "Page size can't be empty")
    @NotNull(message = "Page size can't be empty")
    @Pattern(regexp = "\\d+", message = "Page size format isn't valid")
    private String pageSize;

}
