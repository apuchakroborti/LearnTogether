package com.example.learnTogether.dto;

import com.example.learnTogether.models.UserGroupMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupDto {
    private Long id;
    private String groupName;
    private List<UserGroupMapping> memberList;
    private UserProfileDto userProfile;
}
