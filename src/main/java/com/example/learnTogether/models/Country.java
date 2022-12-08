package com.example.learnTogether.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_COUNTRY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    private String code;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<District> districtList = new ArrayList<>();
}
