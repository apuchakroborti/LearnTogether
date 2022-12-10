package com.apu.example.learnTogether.dto.request;

import com.apu.example.learnTogether.dto.UserGroupDto;
import com.apu.example.learnTogether.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMappingSearchCriteria {
    private Long id;
    private UserProfileDto userProfileDto;
    private UserGroupDto userGroupDto;
}
