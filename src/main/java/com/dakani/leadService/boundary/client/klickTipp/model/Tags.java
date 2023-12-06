package com.dakani.leadService.boundary.client.klickTipp.model;

public enum Tags {
    sie_anrede("8043167"),
    geburtstag_glueckwunsch("1208049"),
    newsletter("1216557"),
    feiertage_frau("1288921"),
    feiertage_mann("1288817"),
    finanzen_de("8466566"),
    egentic_de("9582773"),
    gebäude("8470942"),
    sachversicherungen("8470942"),
    hund("8472359"),
    katze("8472360"),
    pferd("8472361"),
    zusatz("8472367"),
    gewerbehaftpflicht("8472376"),
    gewerbe("8472377"),
    rechtsschutz("8551382"),
    privatehaftpflicht("8551372"),
    hundehalterhaftpflicht("9195477"),
    hausrat("8551377"),
    unfall("10916888"),
    kfz("10916921"),
    pferdehaft("10916930"),
    berufsunfaehigkeit("10916963"),
    geldanlage("10916969"),;
    private final String id;
    Tags(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    }
