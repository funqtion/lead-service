package com.dakani.leadService.service;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.model.EgenticLeadCreateDto;
import com.dakani.leadService.model.mapper.LeadMapper;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.LeadMeta;
import com.dakani.leadService.persistence.entity.Token;
import com.dakani.leadService.persistence.repository.LeadMetaRepository;
import com.dakani.leadService.persistence.repository.TokenRepository;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "leadService")
public class LeadMetaService {
    private final LeadMetaRepository leadMetaRepository;

    private LeadMeta getLeadMeta(String type, long id) {
        return leadMetaRepository.getLeadMeta(type, id);
    }
    @Transactional
    public Either<ExpectedError, LeadMeta> addLeadMeta(LeadMeta leadMeta) {
        return leadMetaRepository.createLeadMeta(leadMeta);
    }
    @Transactional
    public void updateLeadStateDialFire(String type, long id, boolean success) {
        if (success) {
            leadMetaRepository.updateSuccessfulPushDialFire(type, id);
        } else {
            leadMetaRepository.updateFailedPushDialFire(type, id);
        }
    }
    @Transactional
    public void updateLeadStateKlickTipp(String type, long id, boolean success) {
        if (success) {
            leadMetaRepository.updateSuccessfulPushKlickTipp(type, id);
        } else {
            leadMetaRepository.updateFailedPushKlickTipp(type, id);
        }
    }
}
