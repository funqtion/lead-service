package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.entity.LeadMeta;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class LeadMetaRepository {
    private final EntityManager entityManager;
    @Transactional
    public Either<ExpectedError, LeadMeta> createLeadMeta(LeadMeta leadMeta) {
        entityManager.persist(leadMeta);
        return Either.right(leadMeta);
    }
    public LeadMeta getLeadMeta(String type, long id) {
        List<LeadMeta> leadMetas = entityManager
                .createQuery("SELECT lm FROM LeadMeta AS lm WHERE lm.type = :type and lm.leadId = :leadId", LeadMeta.class)
                .setParameter("type", type)
                .setParameter("id", id)
                .getResultList();
        if (leadMetas.isEmpty()) return null;
        else return leadMetas.get(0);
    }

    public void updateSuccessfulPushDialFire(String type, long id) {

        entityManager.createQuery("UPDATE LeadMeta lm SET lm.dialFire = true WHERE lm.leadId = :id and lm.type = :type")
                .setParameter("id", id)
                .setParameter("type", type)
                .executeUpdate();
    }

    public void updateFailedPushDialFire(String type, long id) {
        int id1 = entityManager.createQuery("UPDATE LeadMeta lm SET lm.dialFireError = true WHERE lm.leadId = :id and lm.type = :type")
                .setParameter("id", id)
                .setParameter("type", type)
                .executeUpdate();
    }
    public void updateSuccessfulPushKlickTipp(String type, long id) {

        entityManager.createQuery("UPDATE LeadMeta lm SET lm.klickTipp = true WHERE lm.leadId = :id and lm.type = :type")
                .setParameter("id", id)
                .setParameter("type", type)
                .executeUpdate();
    }

    public void updateFailedPushKlickTipp(String type, long id) {
        int id1 = entityManager.createQuery("UPDATE LeadMeta lm SET lm.klickTippError = true WHERE lm.leadId = :id and lm.type = :type")
                .setParameter("id", id)
                .setParameter("type", type)
                .executeUpdate();

    }


}
