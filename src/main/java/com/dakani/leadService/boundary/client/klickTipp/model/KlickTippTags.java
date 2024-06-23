package com.dakani.leadService.boundary.client.klickTipp.model;

import com.dakani.leadService.persistence.entity.*;
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
            if (finanzenLead.getSubject().toLowerCase().replaceAll("\\s", "").contains(key.name())) {
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

    public KlickTippTags(AnimalLead animalLead) {
        this.email = animalLead.getEmail();
        this.tagids = new ArrayList<>();
        this.tagids.add(Tags.sie_anrede.getId());
        this.tagids.add(Tags.geburtstag_glueckwunsch.getId());
        this.tagids.add(Tags.newsletter.getId());
        if (animalLead.getSalutations().toLowerCase().contains("frau")) {
            this.tagids.add(Tags.feiertage_frau.getId());
        }
        if (animalLead.getSalutations().toLowerCase().contains("herr")) {
            this.tagids.add(Tags.feiertage_mann.getId());
        }
        switch (animalLead.getAnimalType().toLowerCase()) {
            case "dog":
                this.tagids.add(Tags.hund.getId());
                break;
            case "cat":
                this.tagids.add(Tags.katze.getId());
                break;
            case "horse":
                this.tagids.add(Tags.pferd.getId());
                break;
        }
    }

    public KlickTippTags(TeethLead teethLead) {
        this.email = teethLead.getEmail();
        this.tagids = new ArrayList<>();
        this.tagids.add(Tags.sie_anrede.getId());
        this.tagids.add(Tags.geburtstag_glueckwunsch.getId());
        this.tagids.add(Tags.newsletter.getId());
        if (teethLead.getSalutations().toLowerCase().contains("frau")) {
            this.tagids.add(Tags.feiertage_frau.getId());
        }
        if (teethLead.getSalutations().toLowerCase().contains("herr")) {
            this.tagids.add(Tags.feiertage_mann.getId());
        }
    }

    public KlickTippTags(HouseLead houseLead) {
        this.email = houseLead.getEmail();
        this.tagids = new ArrayList<>();
        this.tagids.add(Tags.sie_anrede.getId());
        this.tagids.add(Tags.geburtstag_glueckwunsch.getId());
        this.tagids.add(Tags.newsletter.getId());
        if (houseLead.getSalutations().toLowerCase().contains("frau")) {
            this.tagids.add(Tags.feiertage_frau.getId());
        }
        if (houseLead.getSalutations().toLowerCase().contains("herr")) {
            this.tagids.add(Tags.feiertage_mann.getId());
        }
    }
}
