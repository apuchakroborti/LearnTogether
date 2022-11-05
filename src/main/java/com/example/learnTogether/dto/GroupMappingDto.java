package com.example.learnTogether.dto;

import com.example.learnTogether.utils.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupMappingDto {
    private Long id;
    private UserProfileDto userProfile;
    private UserGroupDto userGroup;
    private MemberRole memberRole;
}
