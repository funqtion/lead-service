package com.dakani.leadService.boundary.client.dialFire.model;


import com.dakani.leadService.persistence.entity.EgenticLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DialFireEgenticRequestBody {
        private String ansprache;
        private String vorname;
        private String nachname;
        private String Geburtstag;
        private String $phone;
        private String email;
        private String strasse;
        private String plz;
        private String ort;
        private String REG_TIME;
        private String REG_ID;
        private String REG_IP;
        private String Sms_Verifizierungszeit_Bestätigung;
        private String Coreg_Frage;
        private String Coreg_Antwort;

        public String get$phone() {
                return $phone;
        }

        public void set$phone(String $phone) {
                this.$phone = $phone;
        }


        public DialFireEgenticRequestBody(EgenticLead egenticLead){
                this.setAnsprache(egenticLead.getSalutation());
                this.setVorname(egenticLead.getFirstName());
                this.setNachname(egenticLead.getLastName());
                this.setGeburtstag(egenticLead.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                this.set$phone(egenticLead.getPhone());
                this.setEmail(egenticLead.getEmail());
                this.setStrasse(egenticLead.getStreet());
                this.setPlz(egenticLead.getPostalCode());
                this.setOrt(egenticLead.getCity());
                this.setREG_TIME(egenticLead.getRegTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                this.setREG_ID(egenticLead.getRegId());
                this.setREG_TIME(egenticLead.getRegTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                this.setSms_Verifizierungszeit_Bestätigung(egenticLead.getSmsVerify().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                this.setCoreg_Frage(egenticLead.getQuestion());
                this.setCoreg_Antwort(egenticLead.getAnswer());
        }
}
