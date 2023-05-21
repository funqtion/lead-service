package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KlickTippTags {
    private String email;
    private List<String> tagids;


    public KlickTippTags(FinanzenLead finanzenLead) {
        this.email = finanzenLead.getEmail();
        this.tagids = new ArrayList<>();
        this.tagids.add(Tags.finanzen_de.getId());
        this.tagids.add(Tags.sie_anrede.getId());
        this.tagids.add(Tags.geburtstag_glueckwunsch.getId());
        this.tagids.add(Tags.newsletter.getId());
        if (finanzenLead.getSalutation().toLowerCase().contains("frau")) {
            this.tagids.add(Tags.feiertage_frau.getId());
        } else {
            this.tagids.add(Tags.feiertage_mann.getId());
        }
        for (Tags key : Tags.values()) {
            if (finanzenLead.getSubject().toLowerCase().contains(key.name())) {
                this.tagids.add(key.getId());
            }
        }
    }
    public KlickTippTags(EgenticLead egenticLead) {
        this.email = egenticLead.getEmail();
        this.tagids = new ArrayList<>();
        this.tagids.add(Tags.egentic_de.getId());
        this.tagids.add(Tags.sie_anrede.getId());
        this.tagids.add(Tags.geburtstag_glueckwunsch.getId());
        this.tagids.add(Tags.newsletter.getId());
        if (egenticLead.getSalutation().toLowerCase().contains("frau")) {
            this.tagids.add(Tags.feiertage_frau.getId());
        } else {
            this.tagids.add(Tags.feiertage_mann.getId());
        }
    }


}
