package com.apu.example.learnTogether.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_DISTRICT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    private String name;
//    private String code;
}
