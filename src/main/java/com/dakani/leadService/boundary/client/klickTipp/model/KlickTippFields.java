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
}
