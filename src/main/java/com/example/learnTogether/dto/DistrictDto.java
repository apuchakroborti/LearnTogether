package com.example.learnTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {
    private Long id;
    @NotNull(message = "Country id must be given!")
    private CountryDto country;
    private String name;
    private String code;
}
