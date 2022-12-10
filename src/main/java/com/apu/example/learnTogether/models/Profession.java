package com.apu.example.learnTogether.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFESSION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profession {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Profession(String name){
        this.name = name;
    }
}
