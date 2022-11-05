package com.example.learnTogether.dto.request;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String useremail;
    private String newpassword;
    private String token;
}
