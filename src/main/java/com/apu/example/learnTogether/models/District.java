package com.apu.example.learnTogether.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_DISTRICT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class District /*implements Serializable */{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
//    @JsonBackReference
    private Country country;

    private String name;
//    private String code;
}
