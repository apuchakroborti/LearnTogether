package com.apu.example.learnTogether.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileSearchCriteria implements Serializable {

    private Long id;
    private String email;
    private String phone;
    private String userId;
    private String firstName;
    private String lastName;
}