package com.apu.example.learnTogether.dto.request;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String useremail;
    private String newpassword;
    private String token;
}
