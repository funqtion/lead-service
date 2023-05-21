package com.dakani.leadService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HouseLeadCreateDto {

    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private String street;
    private String zipCode;
    private String city;
    private String houseType;
    private String buildYear;
    private String objectStreet;
    private String objectZipCode;
    private String objectCity;
    private String objectHouseNumber;
    private String completeHouse;
    private String area;
    private String basement;
    private String basementArea;
    private String levelTop;
    private String protectedHouse;
    private String solarThermal;
    private String photovoltaic;
    private String swimmingPool;
    private String vacationHouse;
    private String extraHouse;
    private String extraHouseArea;
    private String damageFiveYears;
    private String damageAmount;
    private String extraInfo;

}