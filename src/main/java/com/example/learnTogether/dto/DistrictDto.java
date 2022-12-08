package com.example.learnTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto implements Serializable {
    private Long id;
    private String name;
//    private String code;
    private CountryDto country;
}
