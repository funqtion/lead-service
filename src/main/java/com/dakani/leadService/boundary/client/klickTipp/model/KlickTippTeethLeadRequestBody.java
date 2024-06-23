package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.TeethLead;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KlickTippTeethLeadRequestBody {
    private String email;
    @JsonProperty("listid")
    private String listId;
    private KlickTippFields fields;

    public KlickTippTeethLeadRequestBody(TeethLead teethLead) {
        KlickTippFields klickTippFields = new KlickTippFields();
        klickTippFields.setId("TeethFunnel" + teethLead.getId());
        klickTippFields.setFirstName(teethLead.getFirstName());
        klickTippFields.setLastName(teethLead.getLastName());
        klickTippFields.setDateOfBirth(teethLead.getDob());
        klickTippFields.setPhone(teethLead.getPhone());
        klickTippFields.setEmail(teethLead.getEmail());
        klickTippFields.setStreet(teethLead.getStreet());
        klickTippFields.setHouseNumber(teethLead.getHouseNumber());
        klickTippFields.setPostalCode(teethLead.getZipCode());
        klickTippFields.setCity(teethLead.getCity());
        klickTippFields.setData(teethLead.getExtraInfo());
        klickTippFields.setPublicInsurance(teethLead.getPublicInsurance());
        klickTippFields.setAlreadyInTreatment(teethLead.getAlreadyInTreatment());
        klickTippFields.setMissingTeeth(teethLead.getMissingTeeth());
        klickTippFields.setCoverage(teethLead.getCoverage());
        this.setEmail(teethLead.getEmail());
        this.setListId("254408");
        this.setFields(klickTippFields);
    }
}
