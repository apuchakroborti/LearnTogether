package com.example.learnTogether.dto.request;

import lombok.Data;

@Data
public class UserProfileSearchCriteria {

    private Long id;
    private String email;
    private String phone;
    private String userId;
    private String firstName;
    private String lastName;
}