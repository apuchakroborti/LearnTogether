package com.example.learnTogether.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountrySearchCriteria {
    private Long id;
    private String name;
    private String code;
}
