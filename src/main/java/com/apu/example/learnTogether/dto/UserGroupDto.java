package com.apu.example.learnTogether.dto;

import com.apu.example.learnTogether.models.UserGroupMapping;
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
