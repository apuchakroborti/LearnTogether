package com.apu.example.learnTogether.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestHistoryDetailsDto {
    private Long id;
    private TestHistoryDto testHistory;
    private UserQuestionDto userQuestion;
    private OptionDto userSelectedOption;
}
