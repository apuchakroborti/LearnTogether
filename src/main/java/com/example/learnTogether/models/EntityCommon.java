package com.example.learnTogether.models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class EntityCommon implements Serializable {

    @Column(name = "CREATE_TIME" )
    private LocalDateTime createTime;

    @Column(name = "EDIT_TIME" )
    private LocalDateTime editTime;

    @Version
    @Column(name = "INTERNAL_VERSION")
    protected Long version;
}
