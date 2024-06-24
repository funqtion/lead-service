package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.HouseLead;
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
public class KlickTippHouseLeadRequestBody {
    private String email;
    @JsonProperty("listid")
    private String listId;
    private KlickTippFields fields;

    public KlickTippHouseLeadRequestBody(HouseLead houseLead) {
        KlickTippFields klickTippFields = new KlickTippFields();
        klickTippFields.setId("TeethFunnel" + houseLead.getId());
        klickTippFields.setFirstName(houseLead.getFirstName());
        klickTippFields.setLastName(houseLead.getLastName());
        klickTippFields.setDateOfBirth(houseLead.getDob());
        klickTippFields.setPhone(houseLead.getPhone());
        klickTippFields.setEmail(houseLead.getEmail());
        klickTippFields.setStreet(houseLead.getStreet());
        klickTippFields.setHouseNumber(houseLead.getHouseNumber());
        klickTippFields.setPostalCode(houseLead.getZipCode());
        klickTippFields.setCity(houseLead.getCity());
        klickTippFields.setData(houseLead.getExtraInfo());
        klickTippFields.setHouseType(houseLead.getHouseType());
        klickTippFields.setBuildYear(houseLead.getBuildYear());
        klickTippFields.setObjectStreet(houseLead.getObjectStreet());
        klickTippFields.setObjectZipCode(houseLead.getObjectZipCode());
        klickTippFields.setObjectCity(houseLead.getObjectCity());
        klickTippFields.setObjectHouseNumber(houseLead.getObjectHouseNumber());
        klickTippFields.setCompleteHouse(houseLead.getCompleteHouse());
        klickTippFields.setArea(houseLead.getArea());
        klickTippFields.setBasement(houseLead.getBasement());
        klickTippFields.setBasementArea(houseLead.getBasementArea());
        klickTippFields.setLevelTop(houseLead.getLevelTop());
        klickTippFields.setProtectedHouse(houseLead.getProtectedHouse());
        klickTippFields.setSolarThermal(houseLead.getSolarThermal());
        klickTippFields.setPhotovoltaic(houseLead.getPhotovoltaic());
        klickTippFields.setSwimmingPool(houseLead.getSwimmingPool());
        klickTippFields.setVacationHouse(houseLead.getVacationHouse());
        klickTippFields.setExtraHouse(houseLead.getExtraHouse());
        klickTippFields.setExtraHouseArea(houseLead.getExtraHouseArea());
        klickTippFields.setDamageFiveYears(houseLead.getDamageFiveYears());
        klickTippFields.setDamageAmount(Integer.parseInt(houseLead.getDamageAmount()));
        this.setEmail(houseLead.getEmail());
        this.setListId("254408");
        this.setFields(klickTippFields);
    }
}
