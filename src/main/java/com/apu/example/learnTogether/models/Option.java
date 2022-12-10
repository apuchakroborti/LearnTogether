package com.apu.example.learnTogether.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_OPTION")
@Getter
@Setter
public class Option {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALUE", nullable = false)
    private String value;
}
