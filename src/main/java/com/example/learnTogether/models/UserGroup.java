package com.example.learnTogether.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(mappedBy = "userGroup", fetch = FetchType.LAZY)
    private List<UserGroupMapping> memberList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    private UserProfile userProfile;

    private Boolean status;
}
