package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.FinanzenLead;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KlickTippFinanzenRequestBody {
    private String email;
    @JsonProperty("listid")
    private String listId;
    private KlickTippFields fields;

    public KlickTippFinanzenRequestBody(FinanzenLead finanzenLead) {
        KlickTippFields klickTippFields = new KlickTippFields();
        klickTippFields.setFirstName(finanzenLead.getFirstName());
        klickTippFields.setLastName(finanzenLead.getLastName());
        klickTippFields.setStreet(finanzenLead.getStreet());
        klickTippFields.setCity(finanzenLead.getCity());
        klickTippFields.setState(finanzenLead.getState());
        klickTippFields.setPostalCode(finanzenLead.getPostalCode());
        klickTippFields.setCompany(finanzenLead.getCompany());
        klickTippFields.setPhone(finanzenLead.getPhone());
        klickTippFields.setSalutation(finanzenLead.getSalutation());
        klickTippFields.setGender(finanzenLead.getGender());
        klickTippFields.setOccupation(finanzenLead.getOccupation());
        klickTippFields.setSubject(finanzenLead.getSubject());
        klickTippFields.setId(String.valueOf(finanzenLead.getExternalId()));
        klickTippFields.setLeadCreatedAt(finanzenLead.getLeadCreatedAt());
        klickTippFields.setDateOfBirth(finanzenLead.getDob());
        klickTippFields.setData(finanzenLead.getData());
        this.setEmail(finanzenLead.getEmail());
        this.setListId("254408");
        this.setFields(klickTippFields);
    }
}
