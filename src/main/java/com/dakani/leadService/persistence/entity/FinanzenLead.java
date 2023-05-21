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
@Table(name = "finanzen_lead")
public class FinanzenLead {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private int externalId;
    private String salutation;
    private String gender;
    private String firstName;
    private String lastName;
    private String dob;
    private String occupation;
    private String phone;
    private String email;
    private String subject;
    private String data;
    private String street;
    private String postalCode;
    private String city;
    private String state;
    private String company;
    private String leadCreatedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
