package com.dakani.leadService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AnimalLeadCreateDto {
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
    private String animalType;
    private String animalName;
    private String animalBreed;
    private String animalGender;
    private String animalAge;
    private String animalCastration;
    private String selfContribution;
    private String policyType;
    private String extraInfo;

}
