package com.apu.example.learnTogether.dto.response;

import com.apu.example.learnTogether.dto.Pagination;
import com.apu.example.learnTogether.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ServiceResponse<T> implements Serializable{

    private Metadata meta;

    @JsonView({Views.Public.class, Views.UserWithoutQuestions.class})
    private T data;

    private Pagination pagination;

    public ServiceResponse(Metadata meta, T data) {
        this.meta = meta;
        this.data = data;
    }
}