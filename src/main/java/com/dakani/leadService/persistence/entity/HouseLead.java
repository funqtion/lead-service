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
@Table(name = "house_lead")
public class HouseLead {
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
    private String houseType;
    private String buildYear;
    private String objectStreet;
    private String objectZipCode;
    private String objectCity;
    private String objectHouseNumber;
    private String completeHouse;
    private String area;
    private String basement;
    private String basementArea;
    private String levelTop;
    private String protectedHouse;
    private String solarThermal;
    private String photovoltaic;
    private String swimmingPool;
    private String vacationHouse;
    private String extraHouse;
    private String extraHouseArea;
    private String damageFiveYears;
    private String damageAmount;
    private String extraInfo;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
