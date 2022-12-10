package com.apu.example.learnTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto extends CommonDto implements Serializable {
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private ProfessionDto profession;
    private DistrictDto district;
    private Boolean status;
    private List<UserQuestionDto> questionList;
    private List<UserProfileDto> partnerList;
    private List<UserGroupDto> userGroupList;
}
