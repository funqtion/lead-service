package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.entity.AnimalLead;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.LeadMeta;
import com.dakani.leadService.service.LeadMetaService;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class AnimalLeadRepository {

    private final EntityManager entityManager;
    private final LeadMetaService leadMetaService;

    public List<AnimalLead> getLeads() {
        return entityManager
                .createQuery("SELECT el FROM AnimalLead AS el ", AnimalLead.class)
                .getResultList();
    }

    @Transactional
    public Either<ExpectedError, AnimalLead> createLead(AnimalLead animalLead) {
        entityManager.persist(animalLead);
        LeadMeta leadMeta = new LeadMeta();
        leadMeta.setType("animal");
        leadMeta.setLeadId(animalLead.getId());
        leadMetaService.addLeadMeta(leadMeta);
        return Either.right(animalLead);
    }

    public List<AnimalLead> getLeadsToPushToDialFire() {
        return entityManager
                .createQuery("SELECT el FROM AnimalLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'animal' " +
                        "WHERE lm.dialFire = false and lm.dialFireError = false", AnimalLead.class)
                .getResultList();
    }

    public List<AnimalLead> getLeadsToPushToKlickTipp() {
        return entityManager
                .createQuery("SELECT el FROM AnimalLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'animal' " +
                        "WHERE lm.klickTipp = false and lm.klickTippError = false", AnimalLead.class)
                .getResultList();
    }
}
