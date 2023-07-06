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
    private String salutations;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String publicInsurance;
    private String alreadyInTreatment;
    private String missingTeeth;
    private String coverage;
    private String extraInfo;
}