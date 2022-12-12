package com.apu.example.learnTogether.dto;

import com.apu.example.learnTogether.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto implements Serializable {
    @JsonView({Views.Public.class})
    private Long id;

    @JsonView({Views.Public.class})
    private String name;

    @JsonView({Views.Public.class})
    private String code;
}
