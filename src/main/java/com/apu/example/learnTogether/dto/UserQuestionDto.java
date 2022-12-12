package com.apu.example.learnTogether.dto;

import com.apu.example.learnTogether.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionDto extends CommonDto{
    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private Long id;

    @JsonView({Views.UserWithoutQuestions.class})
    @NotNull(message = "user id must be provided!")
    private UserProfileDto userProfile;

    @NotNull(message = "Description should not be null!")
    @NotEmpty(message = "Description should not be empty!")
    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private String description;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    @Size(min = 2, max = 5, message = "option minimum and maximum size should 2 and 5 respectively")
    @NotNull(message = "option list should not be null!")
    private List<OptionDto> optionList;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    @NotNull(message = "answers id must not be null!")
    private OptionDto answer;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private List<CommentDto> commentList;
}
