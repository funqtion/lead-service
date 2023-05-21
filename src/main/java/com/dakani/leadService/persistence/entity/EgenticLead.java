package com.dakani.leadService.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "egentic_lead")
public class EgenticLead {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String salutation;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dob;
    private String phone;
    private String email;
    private String street;
    private String postalCode;
    private String city;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime regTime;
    private String regId;
    private String regIp;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime smsVerify;
    private String question;
    private String answer;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
