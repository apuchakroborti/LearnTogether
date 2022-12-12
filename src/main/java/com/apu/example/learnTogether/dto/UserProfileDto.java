package com.apu.example.learnTogether.dto;

import com.apu.example.learnTogether.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDto extends CommonDto{
    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private Long id;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private String userId;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private String firstName;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private String lastName;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private String email;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private String phone;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private LocalDate dateOfBirth;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private ProfessionDto profession;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private DistrictDto district;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private Boolean status;
//    @JsonIgnore
    @JsonView({Views.UserWithQuestions.class})
    private List<UserQuestionDto> questionList;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private List<UserProfileDto> partnerList;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private List<UserGroupDto> userGroupList;
}
