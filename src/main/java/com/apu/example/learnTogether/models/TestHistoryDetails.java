package com.apu.example.learnTogether.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER_TEST_HISTORY_DETAILS")
@Getter
@Setter
@Data
public class TestHistoryDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEST_HISTORY_ID", nullable = false)
    private TestHistory testHistory;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private UserQuestion userQuestion;

    @ManyToOne
    @JoinColumn(name = "ANSWER_OPTION_ID")
    private Option userSelectedOption;
}
