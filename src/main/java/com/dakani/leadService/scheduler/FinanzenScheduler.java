package com.dakani.leadService.scheduler;


import com.dakani.leadService.boundary.client.finanzen.FinanzenClient;
import com.dakani.leadService.boundary.client.finanzen.model.FinanzenLeadDto;
import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import com.dakani.leadService.service.FinanzenLeadService;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j(topic = "leadservice")
public class FinanzenScheduler {

    private final FinanzenClient finanzenClient;
    private final FinanzenLeadService finanzenLeadService;

    @Scheduled(cron = "${scheduler.finanzen.cron}")
    public void getLeadsFromFinanzen() {
        List<FinanzenLeadDto> finanzenLeadDtos = finanzenClient.pullLastDay();
        for (FinanzenLeadDto finanzenLeadDto : finanzenLeadDtos){
            boolean leadExists = finanzenLeadService.finanzenLeadExists(finanzenLeadDto.getExternalId());
            if (! leadExists) {
                Either<ExpectedError, FinanzenLead> maybeFinanzenLead = finanzenLeadService.createFinanzenLead(finanzenLeadDto);
                if (maybeFinanzenLead.isLeft()) {
                    log.warn("Error creating finanzen lead : {}", maybeFinanzenLead.getLeft().getMessage());
                }
                log.info("Created finanzen lead with id {}", maybeFinanzenLead.get().getId());
            }
        }

    }

}
