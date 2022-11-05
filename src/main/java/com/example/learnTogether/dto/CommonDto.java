package com.example.learnTogether.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonDto {
    private LocalDateTime createTime;
    private LocalDateTime editTime;
}
