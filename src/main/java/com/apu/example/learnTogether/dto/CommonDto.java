package com.apu.example.learnTogether.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonDto implements Serializable {
    private LocalDateTime createTime;
    private LocalDateTime editTime;
}
