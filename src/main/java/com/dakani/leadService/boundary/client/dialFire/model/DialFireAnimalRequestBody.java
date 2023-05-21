package com.dakani.leadService.boundary.client.dialFire.model;

import com.dakani.leadService.persistence.entity.AnimalLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DialFireAnimalRequestBody {
    private String ansprache;
    private String vorname;
    private String nachname;
    private String Geburtstag;
    private String $phone;
    private String Telefonnummer;
    private String email;
    private String strasse;
    private String adresszusatz;
    private String plz;
    private String ort;
    private String TierArt;
    private String TierName;
    private String Geburtstag_Tier;
    private String Geschlecht;
    private String Rasse;
    private String kastriert_sterilisiert;
    private String Selbstbeteiligung___keine_10__20__;
    private String Vorsorgeschutz___Ja_Nein__Preis_;
    private String notiz;



    public String get$phone() {
        return $phone;
    }

    public void set$phone(String $phone) {
        this.$phone = $phone;
    }

    public DialFireAnimalRequestBody(AnimalLead animalLead){
            this.setAnsprache(animalLead.getSalutations());
            this.setVorname(animalLead.getFirstName());
            this.setNachname(animalLead.getLastName());
            this.setGeburtstag(animalLead.getDob());
            this.set$phone(animalLead.getPhone());
            this.setTelefonnummer(animalLead.getPhone());
            this.setEmail(animalLead.getEmail());
            this.setStrasse(animalLead.getStreet());
            this.setAdresszusatz(animalLead.getHouseNumber());
            this.setPlz(animalLead.getZipCode());
            this.setOrt(animalLead.getCity());
            this.setTierArt(animalLead.getAnimalType());
            this.setTierName(animalLead.getAnimalName());
            this.setGeburtstag_Tier(animalLead.getAnimalAge());
            this.setGeschlecht(animalLead.getAnimalGender());
            this.setRasse(animalLead.getAnimalBreed());
            this.setKastriert_sterilisiert(animalLead.getAnimalCastration());
            this.setSelbstbeteiligung___keine_10__20__(animalLead.getSelfContribution());
            this.setVorsorgeschutz___Ja_Nein__Preis_(animalLead.getPolicyType());
            this.setNotiz(animalLead.getExtraInfo());
    }
}


