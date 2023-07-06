package com.dakani.leadService.boundary.client.dialFire.model;

import com.dakani.leadService.persistence.entity.TeethLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DialFireTeethRequestBody {
    private String ansprache;
    private String vorname;
    private String nachname;
    private String geburtstag;
    private String $phone;
    private String Telefonnummer;
    private String email;
    private String strasse;
    private String adresszusatz;
    private String plz;
    private String ort;
    private String Bist_du_in_der_GKV_;
    private String Zahn_Behandlung;
    private String fehlende_Zaehne;
    private String Absicherung_prozent;
    private String notizfeld;

    public String get$phone() {
        return $phone;
    }

    public void set$phone(String $phone) {
        this.$phone = $phone;
    }
    public DialFireTeethRequestBody(TeethLead teethLead) {
        this.setAnsprache(teethLead.getSalutations());
        this.setVorname(teethLead.getFirstName());
        this.setNachname(teethLead.getLastName());
        this.setGeburtstag(teethLead.getDob());
        this.set$phone(teethLead.getPhone());
        this.setTelefonnummer(teethLead.getPhone());
        this.setEmail(teethLead.getEmail());
        this.setStrasse(teethLead.getStreet());
        this.setAdresszusatz(teethLead.getHouseNumber());
        this.setPlz(teethLead.getZipCode());
        this.setOrt(teethLead.getCity());
        this.setBist_du_in_der_GKV_(teethLead.getPublicInsurance());
        this.setZahn_Behandlung(teethLead.getAlreadyInTreatment());
        this.setFehlende_Zaehne(teethLead.getMissingTeeth());
        this.setAbsicherung_prozent(teethLead.getCoverage());
        this.setNotizfeld(teethLead.getExtraInfo());
    }

}
