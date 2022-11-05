package com.example.learnTogether.dto.request;

import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.dto.UserQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSearchCriteria {
    private Long id;
    private UserProfileDto userProfileDto;
    private UserQuestionDto userQuestionDto;
    private Boolean status;
}
