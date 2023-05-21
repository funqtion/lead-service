package com.dakani.leadService.boundary.client.finanzen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FinanzenLeadDto {
    private String externalId;
    private String salutation;
    private String gender;
    private String firstName;
    private String lastName;
    private String dob;
    private String occupation;
    private String phone;
    private String email;
    private String subject;
    private String data;
    private String street;
    private String postalCode;
    private String city;
    private String state;
    private String company;
    private String leadCreatedAt;



//    @SuppressWarnings("unchecked")
    public FinanzenLeadDto(Map<String,Object> rawLead) {
        Map<String,Object> customer = (Map<String,Object>)rawLead.get("customer");
        Map<String,Object> contact = (Map<String,Object>)customer.get("contact");
        Map<String,Object> plz = (Map<String,Object>)customer.get("postalArea");
        Map<String,Object> occupationGroup = (Map<String,Object>)customer.get("occupationGroup");
        Map<String,Object> product = (Map<String,Object>)rawLead.get("product");
        Map<String,Object> createdAt = (Map<String,Object>)rawLead.get("createdAt");
        this.externalId = String.valueOf(rawLead.get("id"));
        this.firstName = String.valueOf(contact.get("firstName"));
        this.lastName = String.valueOf(contact.get("lastName"));
        this.dob = String.valueOf(customer.get("dateOfBirth"));
        this.phone = String.valueOf(customer.get("phone"));
        this.occupation = String.valueOf(occupationGroup.get("name"));
        this.email = String.valueOf(customer.get("email"));
        this.subject = String.valueOf(product.get("name"));
        this.street = String.valueOf(customer.get("street"));
        this.postalCode = String.valueOf(customer.get("postalCode"));
        this.city = String.valueOf(customer.get("city"));
        this.state = String.valueOf(plz.get("region1"));
        this.company = String.valueOf(contact.get("company"));
        this.leadCreatedAt = String.valueOf(createdAt.get("date"));
        this.data = String.valueOf(rawLead.get("data"));

        int sex = Integer.parseInt(String.valueOf(contact.get("sex")));
        if (sex == 1) {
            this.salutation = "Herr";
            this.gender = "Männlich";
        } else if (sex == 2) {
            this.salutation = "Frau";
            this.gender = "Weiblich";
        } else if (sex == 0) {
            this.salutation = "Divers";
            this.gender = "Divers";
        } else {
            this.salutation = "Unbekannt";
            this.gender = "Unbekannt";
        }
    }


}

/*
payload = {
        
        if payload["salutation"] == 1:
        payload["salutation"] = "Herr"
        payload["gender"] = "Männlich"
        elif payload["salutation"] == 2:
        payload["salutation"] = "Frau"
        payload["gender"] = "Weiblich"
        elif payload["salutation"] == 0:
        payload["salutation"] = "Divers"
        payload["gender"] = "Divers"
        else:
        payload["salutation"] = "Unbekannt"

        payload["data"] = json.loads(
        json.dumps(payload["data"], ensure_ascii=False).replace("\xa0", " ")
        )*/
