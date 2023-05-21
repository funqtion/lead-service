package com.dakani.leadService.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "teeth_lead")
public class TeethLead {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String salutations;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String birthday;
    private String publicInsurance;
    private String alreadyInTreatment;
    private String missingTeeth;
    private String coverage;
    private String extraInfo;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}