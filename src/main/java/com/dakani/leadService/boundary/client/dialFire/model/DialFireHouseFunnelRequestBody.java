package com.dakani.leadService.boundary.client.dialFire.model;

import com.dakani.leadService.persistence.entity.HouseLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DialFireHouseFunnelRequestBody {
    private String Ansprache_whg;
    private String Vorname_whg;
    private String Nachname_whg;
    private String Geburtstag_whg;
    private String $phone;
    private String Telefonnummer_whg;
    private String E_Mail_whg;
    private String Strasse_whg;
    private String Haus_Nr_whg;
    private String PLZ_whg;
    private String Ort_whg;
    private String Gebäude_Art_whg;
    private String Baujahr_whg;
    private String Strasse_Gebäude;
    private String Hausnummer_Gebäude;
    private String PLZ_Gebäude;
    private String Stadt_Gebäude;
    private String Fertighaus;
    private String Keller_vorhanden;
    private String Wenn_Ja__Keller_wohnfläche;
    private String Wohnfläche_qm;
    private String Flachdach;
    private String Denkmalgeschützt;
    private String Solaranlage;
    private String Photovoltaik;
    private String Ferienhaus;
    private String Schwimmbad;
    private String Zusätzliches_Gebäude_vorhanden;
    private String nebengebäue_qm;
    private String schäden_letzten_5_Jahre;
    private String shäden_summe;
    private String notiz;
    private String Leadherkunft;

    public String get$phone() {
        return $phone;
    }

    public void set$phone(String $phone) {
        this.$phone = $phone;
    }

    public DialFireHouseFunnelRequestBody(HouseLead houseLead) {
        this.setAnsprache_whg(houseLead.getSalutations());
        this.setVorname_whg(houseLead.getFirstName());
        this.setNachname_whg(houseLead.getLastName());
        this.setGeburtstag_whg(houseLead.getDob());
        this.set$phone(houseLead.getPhone());
        this.setTelefonnummer_whg(houseLead.getPhone());
        this.setE_Mail_whg(houseLead.getEmail());
        this.setStrasse_whg(houseLead.getStreet());
        this.setHaus_Nr_whg(houseLead.getHouseNumber());
        this.setPLZ_whg(houseLead.getZipCode());
        this.setOrt_whg(houseLead.getCity());
        this.setGebäude_Art_whg(houseLead.getHouseType());
        this.setBaujahr_whg(houseLead.getBuildYear());
        this.setStrasse_Gebäude(houseLead.getObjectStreet());
        this.setHausnummer_Gebäude(houseLead.getObjectHouseNumber());
        this.setPLZ_Gebäude(houseLead.getObjectZipCode());
        this.setStadt_Gebäude(houseLead.getObjectCity());
        this.setFertighaus(houseLead.getCompleteHouse());
        this.setKeller_vorhanden(houseLead.getBasement());
        this.setWenn_Ja__Keller_wohnfläche(houseLead.getBasementArea());
        this.setWohnfläche_qm(houseLead.getArea());
        this.setFlachdach(houseLead.getLevelTop());
        this.setDenkmalgeschützt(houseLead.getProtectedHouse());
        this.setSolaranlage(houseLead.getSolarThermal());
        this.setPhotovoltaik(houseLead.getPhotovoltaic());
        this.setFerienhaus(houseLead.getVacationHouse());
        this.setSchwimmbad(houseLead.getSwimmingPool());
        this.setZusätzliches_Gebäude_vorhanden(houseLead.getExtraHouse());
        this.setNebengebäue_qm(houseLead.getExtraHouseArea());
        this.setSchäden_letzten_5_Jahre(houseLead.getDamageFiveYears());
        this.setShäden_summe(houseLead.getDamageAmount());
        this.setNotiz(houseLead.getExtraInfo());
        this.setLeadherkunft("houseFunnel");
    }



}
