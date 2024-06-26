package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.AnimalLead;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KlickTippAnimalLeadRequestBody {
    private String email;
    @JsonProperty("listid")
    private String listId;
    private KlickTippFields fields;

    public KlickTippAnimalLeadRequestBody(AnimalLead animalLead) {
        KlickTippFields klickTippFields = new KlickTippFields();
        klickTippFields.setId("AnimalFunnel" + animalLead.getId());
        klickTippFields.setFirstName(animalLead.getFirstName());
        klickTippFields.setLastName(animalLead.getLastName());
        klickTippFields.setDateOfBirth(animalLead.getDob());
        klickTippFields.setPhone(animalLead.getPhone());
        klickTippFields.setEmail(animalLead.getEmail());
        klickTippFields.setStreet(animalLead.getStreet());
        klickTippFields.setHouseNumber(animalLead.getHouseNumber());
        klickTippFields.setPostalCode(animalLead.getZipCode());
        klickTippFields.setCity(animalLead.getCity());
        klickTippFields.setAnimalName(animalLead.getAnimalName());
        klickTippFields.setPolicyType(animalLead.getPolicyType());
        klickTippFields.setData(animalLead.getExtraInfo());
        switch (animalLead.getAnimalType()) {
            case "dog":
                LocalDate dogDob = LocalDate.parse(animalLead.getAnimalAge());
                long dogDobEpoch = dogDob.atStartOfDay().toEpochSecond(UTC);
                klickTippFields.setDogAge(dogDobEpoch);
                klickTippFields.setDogBreed(animalLead.getAnimalBreed());
                klickTippFields.setDogGender(animalLead.getAnimalGender());
                klickTippFields.setDogCastrated(animalLead.getAnimalCastration());
                klickTippFields.setDogSelfContribution(animalLead.getSelfContribution());
                break;
            case "cat":
                LocalDate catDob = LocalDate.parse(animalLead.getAnimalAge());
                long catDobEpoch = catDob.atStartOfDay().toEpochSecond(UTC);
                klickTippFields.setCatAge(catDobEpoch);
                klickTippFields.setCatBreed(animalLead.getAnimalBreed());
                klickTippFields.setCatGender(animalLead.getAnimalGender());
                klickTippFields.setCatCastrated(animalLead.getAnimalCastration());
                klickTippFields.setCatSelfContribution(animalLead.getSelfContribution());
                break;
            case "horse":
                LocalDate horseDob = LocalDate.parse(animalLead.getAnimalAge());
                long horseDobEpoch = horseDob.atStartOfDay().toEpochSecond(UTC);
                klickTippFields.setHorseAge(horseDobEpoch);
                klickTippFields.setHorseBreed(animalLead.getAnimalBreed());
                klickTippFields.setHorseGender(animalLead.getAnimalGender());
                klickTippFields.setHorseCastrated(animalLead.getAnimalCastration());
                klickTippFields.setHorseSelfContribution(animalLead.getSelfContribution());
                break;
        }
        this.setEmail(animalLead.getEmail());
        this.setListId("254408");
        this.setFields(klickTippFields);
    }
}
