package com.dakani.leadService.boundary.client.klickTipp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KlickTippFields {
    @JsonProperty("fieldFirstName")
    private String firstName;
    @JsonProperty("fieldLastName")
    private String lastName;
    @JsonProperty("fieldStreet1")
    private String street;
    @JsonProperty("field206789")
    private String houseNumber;
    @JsonProperty("fieldCity")
    private String city;
    @JsonProperty("fieldState")
    private String state;
    @JsonProperty("fieldZip")
    private String postalCode;
    @JsonProperty("fieldCompanyName")
    private String company;
    @JsonProperty("fieldPhone")
    private String phone;
    @JsonProperty("field157202")
    private String salutation;
    @JsonProperty("field157376")
    private String gender;
    @JsonProperty("field157204")
    private String occupation;
    @JsonProperty("field164400")
    private String email;
    @JsonProperty("field164401")
    private String subject;
    @JsonProperty("field164894")
    private String id;
    @JsonProperty("field164895")
    private String leadCreatedAt;
    @JsonProperty("field165010")
    private String dateOfBirth;
    @JsonProperty("field165011")
    private String data;

//    Animal Funnel Fields
    @JsonProperty("field157203")
    private String animalName;
    @JsonProperty("field171228")
    private Long dogAge;
    @JsonProperty("field171431")
    private Long catAge;
    @JsonProperty("field171435")
    private Long horseAge;
    @JsonProperty("field171231")
    private String dogBreed;
    @JsonProperty("field171006")
    private String catBreed;
    @JsonProperty("field171433")
    private String horseBreed;
    @JsonProperty("field171436")
    private String horseCastrated;
    @JsonProperty("field171010")
    private String catCastrated;
    @JsonProperty("field171226")
    private String dogCastrated;
    @JsonProperty("field171229")
    private String dogGender;
    @JsonProperty("field171007")
    private String catGender;
    @JsonProperty("field171434")
    private String horseGender;
    @JsonProperty("field171232")
    private String dogSelfContribution;
    @JsonProperty("field171013")
    private String catSelfContribution;
    @JsonProperty("field171438")
    private String horseSelfContribution;
    @JsonProperty("field206794")
    private String policyType;

//    Teeth Funnel Fields
    @JsonProperty("field206795")
    private String publicInsurance;
    @JsonProperty("field173331")
    private String alreadyInTreatment;
    @JsonProperty("field173332")
    private String missingTeeth;
    @JsonProperty("field206796")
    private String coverage;

//    House Funnel Fields
    @JsonProperty("field206798")
    private String houseType;
    @JsonProperty("field173182")
    private String buildYear;
    @JsonProperty("field195932")
    private String objectStreet;
    @JsonProperty("field206797")
    private String objectHouseNumber;
    @JsonProperty("field195934")
    private String objectZipCode;
    @JsonProperty("field195933")
    private String objectCity;
    @JsonProperty("field206799")
    private String completeHouse;
    @JsonProperty("field173184")
    private String area;
    @JsonProperty("field173185")
    private String basement;
    @JsonProperty("field173186")
    private String basementArea;
    @JsonProperty("field195948")
    private String levelTop;
    @JsonProperty("field206800")
    private String protectedHouse;
    @JsonProperty("field206801")
    private String solarThermal;
    @JsonProperty("field206802")
    private String photovoltaic;
    @JsonProperty("field206803")
    private String vacationHouse;
    @JsonProperty("field206804")
    private String swimmingPool;
    @JsonProperty("field173188")
    private String extraHouse;
    @JsonProperty("field206832")
    private String extraHouseArea;
    @JsonProperty("field206833")
    private String damageFiveYears;
    @JsonProperty("field195944")
    private Integer damageAmount;
}
