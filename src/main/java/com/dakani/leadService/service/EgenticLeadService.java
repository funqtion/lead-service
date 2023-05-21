package com.dakani.leadService.service;

import com.dakani.leadService.model.EgenticLeadCreateDto;
import com.dakani.leadService.model.mapper.LeadMapper;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.repository.EgenticLeadRepository;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "leadService")
public class EgenticLeadService {
    private final EgenticLeadRepository egenticLeadRepository;

    @Transactional
    public Either<ExpectedError, EgenticLead> addEgenticLead(EgenticLeadCreateDto egenticLeadDto) {
        EgenticLead egenticLead = LeadMapper.INSTANCE.egenticLeadCreateDtoToEgenticLead(egenticLeadDto);
        if(egenticLead.getPostalCode().length() == 4) {
            egenticLead.setPostalCode("0" + egenticLead.getPostalCode());
        }
        return egenticLeadRepository.createLead(egenticLead);
    }

    public List<EgenticLead> getEgenticLeadsToPushToDialFire() {
        return egenticLeadRepository.getLeadsToPushToDialFire();
    }
    public List<EgenticLead> getEgenticLeadsToPushToKlickTipp() {
        return egenticLeadRepository.getLeadsToPushToKlickTipp();
    }
}
