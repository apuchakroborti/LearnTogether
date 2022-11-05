package com.example.learnTogether.models;

import com.example.learnTogether.utils.MemberRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_GROUP_MAPPING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupMapping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private UserGroup userGroup;


    @Column(name = "MEMBER_ROLE")
    private MemberRole memberRole;
}
