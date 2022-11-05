package com.example.learnTogether.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//using @Getter and @Setter instead of @Data to remove recursive toString method
@Entity
@Table(name = "USER_PROFILE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends EntityCommon{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", nullable = false, length = 120, unique = true)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESSION_ID")
    private Profession profession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="oauth_user_id")
    private User oauthUser;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<UserQuestion> questionList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_PARTNER_MAPPING",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PARTNER_ID", referencedColumnName = "id"))
    @OrderBy
    private List<UserProfile> partnerList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_GROUP_MAPPING",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "id"))
    @OrderBy
    private List<UserGroup> userGroupList = new ArrayList<>();

}
