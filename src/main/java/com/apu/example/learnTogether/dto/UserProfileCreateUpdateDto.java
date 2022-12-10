package com.apu.example.learnTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileCreateUpdateDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;

    @NotNull(message = "Profession must not be null")
    private Long professionId;

    @NotNull(message = "District must not be null")
    private Long districtId;

    @NotNull(message = "Country must not be null")
    private Long  countryId;

    private String password;
}
