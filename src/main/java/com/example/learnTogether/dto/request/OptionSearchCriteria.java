package com.example.learnTogether.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionSearchCriteria {
    private Long id;
    private String value;
}
