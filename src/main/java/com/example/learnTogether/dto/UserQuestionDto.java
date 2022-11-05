package com.example.learnTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionDto {
    private Long id;
    private UserProfileDto userProfile;
    private String description;
    private List<OptionDto> optionList;
    private OptionDto answer;
    private List<CommentDto> commentList;
}
