package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.error.ExpectedError;
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
public class EgenticLeadRepository {

    private final EntityManager entityManager;
    private final LeadMetaService leadMetaService;

    public List<EgenticLead> getLeads() {
        return entityManager
                .createQuery("SELECT el FROM EgenticLead AS el ", EgenticLead.class)
                .getResultList();
    }

    @Transactional
    public Either<ExpectedError, EgenticLead> createLead(EgenticLead egenticLead) {
        entityManager.persist(egenticLead);
        LeadMeta leadMeta = new LeadMeta();
        leadMeta.setType("egentic");
        leadMeta.setLeadId(egenticLead.getId());
        leadMetaService.addLeadMeta(leadMeta);
        return Either.right(egenticLead);
    }

    public List<EgenticLead> getLeadsToPushToDialFire() {
        return entityManager
                .createQuery("SELECT el FROM EgenticLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'egentic' " +
                        "WHERE lm.dialFire = false and lm.dialFireError = false", EgenticLead.class)
                .getResultList();
    }

    public List<EgenticLead> getLeadsToPushToKlickTipp() {
        return entityManager
                .createQuery("SELECT el FROM EgenticLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'egentic' " +
                        "WHERE lm.klickTipp = false and lm.klickTippError = false", EgenticLead.class)
                .getResultList();
    }
}
