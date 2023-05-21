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
@Table(name = "animal_lead")
public class AnimalLead {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private String street;
    private String zipCode;
    private String city;
    private String animalType;
    private String animalName;
    private String animalBreed;
    private String animalGender;
    private String animalAge;
    private String animalCastration;
    private String selfContribution;
    private String policyType;
    private String extraInfo;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}