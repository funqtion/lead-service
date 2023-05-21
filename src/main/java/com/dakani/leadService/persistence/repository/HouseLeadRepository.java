package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.entity.AnimalLead;
import com.dakani.leadService.persistence.entity.HouseLead;
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
public class HouseLeadRepository {

    private final EntityManager entityManager;
    private final LeadMetaService leadMetaService;

    public List<HouseLead> getLeads() {
        return entityManager
                .createQuery("SELECT el FROM HouseLead AS el ", HouseLead.class)
                .getResultList();
    }

    @Transactional
    public Either<ExpectedError, HouseLead> createLead(HouseLead houseLead) {
        entityManager.persist(houseLead);
        LeadMeta leadMeta = new LeadMeta();
        leadMeta.setType("house");
        leadMeta.setLeadId(houseLead.getId());
        leadMetaService.addLeadMeta(leadMeta);
        return Either.right(houseLead);
    }

    public List<HouseLead> getLeadsToPushToDialFire() {
        return entityManager
                .createQuery("SELECT el FROM HouseLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'house' " +
                        "WHERE lm.dialFire = false and lm.dialFireError = false", HouseLead.class)
                .getResultList();
    }

    public List<HouseLead> getLeadsToPushToKlickTipp() {
        return entityManager
                .createQuery("SELECT el FROM HouseLead AS el " +
                        "JOIN LeadMeta AS lm on lm.leadId = el.id and lm.type = 'house' " +
                        "WHERE lm.klickTipp = false and lm.klickTippError = false", HouseLead.class)
                .getResultList();
    }
}
