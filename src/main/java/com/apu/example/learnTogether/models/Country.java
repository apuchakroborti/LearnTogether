package com.apu.example.learnTogether.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_COUNTRY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country /*implements Serializable */{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    private String code;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<District> districtList = new ArrayList<>();
}
