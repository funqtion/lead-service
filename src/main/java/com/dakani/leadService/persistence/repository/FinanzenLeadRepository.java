package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.entity.FinanzenLead;
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
public class FinanzenLeadRepository {

    private final EntityManager entityManager;
    private final LeadMetaService leadMetaService;


    public List<FinanzenLead> getLeads() {
        return entityManager
                .createQuery("SELECT el FROM FinanzenLead AS el ", FinanzenLead.class)
                .getResultList();
    }

    public boolean leadExists(String externalId) {
        List<FinanzenLead> oldLeads = entityManager
                .createQuery("SELECT el FROM FinanzenLead AS el WHERE el.externalId = :externalId", FinanzenLead.class)
                .setParameter("externalId", externalId)
                .getResultList();
        return oldLeads.size() > 0;
    }

    @Transactional
    public Either<ExpectedError, FinanzenLead> createLead(FinanzenLead finanzenLead) {
        entityManager.persist(finanzenLead);
        LeadMeta leadMeta = new LeadMeta();
        leadMeta.setType("finanzen");
        leadMeta.setLeadId(finanzenLead.getId());
        leadMetaService.addLeadMeta(leadMeta);
        return Either.right(finanzenLead);
    }

    public List<FinanzenLead> getLeadsToPushToKlickTipp() {
        return entityManager
                .createQuery("SELECT el FROM FinanzenLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'finanzen' " +
                        "WHERE lm.klickTipp = false and lm.klickTippError = false", FinanzenLead.class)
                .getResultList();
    }

    public List<FinanzenLead> getLeadsToPushToDialFire() {
        return entityManager
                .createQuery("SELECT el FROM FinanzenLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'finanzen' " +
                        "WHERE lm.dialFire = false and lm.dialFireError = false", FinanzenLead.class)
                .getResultList();
    }


}