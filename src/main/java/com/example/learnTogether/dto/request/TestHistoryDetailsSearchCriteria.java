package com.example.learnTogether.dto.request;

import com.example.learnTogether.dto.TestHistoryDto;
import com.example.learnTogether.dto.UserQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestHistoryDetailsSearchCriteria {
    private Long id;
    private TestHistoryDto testHistoryDto;
    private UserQuestionDto userQuestionDto;
}
