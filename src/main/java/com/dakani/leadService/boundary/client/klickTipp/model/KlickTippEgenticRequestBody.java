package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.EgenticLead;
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
public class KlickTippEgenticRequestBody {
    private String email;
    @JsonProperty("listid")
    private String listId;
    private KlickTippFields fields;

    public KlickTippEgenticRequestBody(EgenticLead egenticLead) {

        KlickTippFields klickTippFields = new KlickTippFields();
        klickTippFields.setFirstName(egenticLead.getFirstName());
        klickTippFields.setLastName(egenticLead.getLastName());
        klickTippFields.setStreet(egenticLead.getStreet());
        klickTippFields.setCity(egenticLead.getCity());
        klickTippFields.setPostalCode(egenticLead.getPostalCode());
        klickTippFields.setPhone(egenticLead.getPhone());
        klickTippFields.setSalutation(egenticLead.getSalutation());
        klickTippFields.setId(String.valueOf(egenticLead.getRegId()));
        klickTippFields.setLeadCreatedAt(String.valueOf(egenticLead.getRegTime()));
        klickTippFields.setDateOfBirth(String.valueOf(egenticLead.getDob()));
        this.setEmail(egenticLead.getEmail());
        this.setListId("254408");
        this.setFields(klickTippFields);
    }
}
