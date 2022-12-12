package com.apu.example.learnTogether.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_QUESTION")
@Getter
@Setter
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestion extends EntityCommon{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserProfile userProfile;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "ELABORATION", nullable = false)
    private String elaboration;

    //TODO need to join another table to fetch all options data
    //TODO for every question this option list is called once--> Q?Ensure this is N+1 problem or not
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "QUESTION_OPTION_MAPPING",
            joinColumns = @JoinColumn(name = "QUESTION_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "OPTION_ID", referencedColumnName = "id"))
    @OrderBy
    private List<Option> optionList = new ArrayList<>();

    //TODO need to join another table to fetch all options data
    @ManyToOne
    @JoinColumn(name = "ANSWER_OPTION_ID")
    private Option answer;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Comment> commentList = new ArrayList<>();

    private Boolean status;
}
