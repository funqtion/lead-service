package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.entity.LeadMeta;
import com.dakani.leadService.persistence.entity.TeethLead;
import com.dakani.leadService.service.LeadMetaService;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class TeethLeadRepository {

    private final EntityManager entityManager;
    private final LeadMetaService leadMetaService;

    public List<TeethLead> getLeads() {
        return entityManager
                .createQuery("SELECT el FROM TeethLead AS el ", TeethLead.class)
                .getResultList();
    }

    @Transactional
    public Either<ExpectedError, TeethLead> createLead(TeethLead teethLead) {
        entityManager.persist(teethLead);
        LeadMeta leadMeta = new LeadMeta();
        leadMeta.setType("teeth");
        leadMeta.setLeadId(teethLead.getId());
        leadMetaService.addLeadMeta(leadMeta);
        return Either.right(teethLead);
    }

    public List<TeethLead> getLeadsToPushToDialFire() {
        return entityManager
                .createQuery("SELECT el FROM TeethLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'teeth' " +
                        "WHERE lm.dialFire = false and lm.dialFireError = false", TeethLead.class)
                .getResultList();
    }

    public List<TeethLead> getLeadsToPushToKlickTipp() {
        return entityManager
                .createQuery("SELECT el FROM TeethLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'teeth' " +
                        "WHERE lm.klickTipp = false and lm.klickTippError = false", TeethLead.class)
                .getResultList();
    }
}
