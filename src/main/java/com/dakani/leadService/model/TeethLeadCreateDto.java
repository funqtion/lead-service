package com.dakani.leadService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TeethLeadCreateDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String dob;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String street;
    @NotNull
    private String zipCode;
    @NotNull
    private String city;
    @NotNull
    private String birthday;
    @NotNull
    private String publicInsurance;
    @NotNull
    private String alreadyInTreatment;
    @NotNull
    private String missingTeeth;
    @NotNull
    private String coverage;
    @NotNull
    private String extraInfo;

}