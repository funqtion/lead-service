package com.dakani.leadService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EgenticLeadCreateDto {

    private String salutation; //ANREDE
    private String firstName;//VORNAME
    private String lastName;//NACHNAME
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dob;//    GEBURTSDATUM
    private String phone;//    TELEFON
    private String email;//    EMAIL
    private String street;// STRASSE
    private String postalCode;//    PLZ
    private String city;//    ORT
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime regTime;//    REG_TIME
    private String regId;//    REG_ID
    private String regIp;//    REG_IP
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime smsVerify;//Sms-Verifizierungszeit-Bestätigung
    private String question; //    Coreg-Frage
    private String answer; //    Coreg-Antwort



}
