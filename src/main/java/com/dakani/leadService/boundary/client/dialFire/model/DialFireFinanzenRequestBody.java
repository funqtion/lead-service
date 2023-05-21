package com.dakani.leadService.boundary.client.dialFire.model;


import ch.qos.logback.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DialFireFinanzenRequestBody {
private long ID;
private String ansprache;
private String vorname;
private String nachname;
private String Geburtstag;
private String $phone;
private String strasse;
private String plz;
private String ort;
private String Produkt;
private String email;
private String notiz;
private String Berufsgruppe;
private String Leadherkunft;
private String Kaufdatum;

        public String get$phone() {
                return $phone;
        }

        public void set$phone(String $phone) {
                this.$phone = $phone;
        }

        public DialFireFinanzenRequestBody(FinanzenLead finanzenLead){

                this.setID(finanzenLead.getExternalId());
                this.setAnsprache(finanzenLead.getSalutation());
                this.setVorname(finanzenLead.getFirstName());
                this.setNachname(finanzenLead.getLastName());
                this.setGeburtstag(finanzenLead.getDob());
                this.setBerufsgruppe(finanzenLead.getOccupation());
                this.set$phone(finanzenLead.getPhone());
                this.setEmail(finanzenLead.getEmail());
                this.setProdukt(finanzenLead.getSubject());
                this.setNotiz(finanzenLead.getData());
                this.setStrasse(finanzenLead.getStreet());
                this.setPlz(finanzenLead.getPostalCode());
                this.setOrt(finanzenLead.getCity());
                this.setLeadherkunft("finanzen.de");
                this.setKaufdatum(finanzenLead.getLeadCreatedAt());
        }
}
