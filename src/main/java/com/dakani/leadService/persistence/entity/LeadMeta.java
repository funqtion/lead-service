package com.dakani.leadService.persistence.entity;

import jakarta.persistence.*;
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
@Table(name = "lead_meta",
        uniqueConstraints={
        @UniqueConstraint(columnNames = {"leadId", "type"})
})
public class LeadMeta {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private long leadId;
    private String type;
    private boolean dialFire;
    private boolean klickTipp;
    private boolean dialFireError;
    private boolean klickTippError;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
