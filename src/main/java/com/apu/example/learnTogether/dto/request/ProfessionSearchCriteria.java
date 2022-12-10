package com.apu.example.learnTogether.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionSearchCriteria {
    private Long id;
    private String name;
}
