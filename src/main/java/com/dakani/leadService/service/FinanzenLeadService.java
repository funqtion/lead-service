package com.dakani.leadService.service;

import com.dakani.leadService.boundary.client.finanzen.model.FinanzenLeadDto;
import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.model.mapper.LeadMapper;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import com.dakani.leadService.persistence.repository.FinanzenLeadRepository;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "leadService")
public class FinanzenLeadService {
    private final FinanzenLeadRepository finanzenLeadRepository;

    @Transactional
    public Either<ExpectedError, FinanzenLead> createFinanzenLead(FinanzenLeadDto finanzenLeadDto) {
        FinanzenLead finanzenLead = LeadMapper.INSTANCE.finanzenLeadDtoToFinanzenLead(finanzenLeadDto);
        return finanzenLeadRepository.createLead(finanzenLead);
    }

    public boolean finanzenLeadExists(String externalId) {
        return finanzenLeadRepository.leadExists(externalId);
    }
    public List<FinanzenLead> getFinanzenLeadsToPushToKlickTipp() {
        return finanzenLeadRepository.getLeadsToPushToKlickTipp();
    }
    public List<FinanzenLead> getFinanzenLeadsToPushToDialFire() {
        return finanzenLeadRepository.getLeadsToPushToDialFire();
    }

}
