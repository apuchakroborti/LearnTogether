package com.apu.example.learnTogether.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_TEST_HISTORY")
@Getter
@Setter
@Data
public class TestHistory extends EntityCommon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEST_NAME", nullable = false)
    private String testName;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserProfile userProfile;

    @Column(name = "TOTAL_QUESTIONS")
    private Integer totalQuestions;

    @Column(name = "TOTAL_CORRECT")
    private Integer totalCorrect;

    @Column(name = "TOTAL_WRONG")
    private Integer totalWrong;

    @Column(name = "DID_NOT_TOUCH")
    private Integer didNotTouch;

    @Column(name = "TEST_START_TIME")
    private LocalDateTime testStartTime;

    @Column(name = "TEST_END_TIME")
    private LocalDateTime testEndTime;

    private Boolean status;

    @OneToMany(mappedBy = "testHistory", fetch = FetchType.LAZY )
    List<TestHistoryDetails> userTestHistoryDetailsList = new ArrayList<>();
}
