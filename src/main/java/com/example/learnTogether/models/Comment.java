package com.example.learnTogether.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_COMMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends EntityCommon{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private UserQuestion question;

    @Column(name = "STATUS")
    private Boolean status;

}
