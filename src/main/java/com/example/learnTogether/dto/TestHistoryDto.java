package com.example.learnTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestHistoryDto {
    private Long id;
    private String testName;
    private UserProfileDto userProfile;
    private Integer totalQuestions;
    private Integer totalCorrect;
    private Integer totalWrong;
    private Integer didNotTouch;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    List<TestHistoryDetailsDto> userTestHistoryDetailsList;
}
