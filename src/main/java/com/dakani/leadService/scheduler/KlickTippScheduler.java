package com.dakani.leadService.scheduler;


import com.dakani.leadService.boundary.client.klickTipp.KlickTippClient;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import com.dakani.leadService.service.EgenticLeadService;
import com.dakani.leadService.service.FinanzenLeadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j(topic = "leadservice")
public class KlickTippScheduler {

    private final EgenticLeadService egenticLeadService;
    private final FinanzenLeadService finanzenLeadService;
    private final KlickTippClient klickTippClient;

    @Scheduled(cron = "${scheduler.klickTipp.cron}")
    public void postNewLeadsToKlickTipp() {
        List<FinanzenLead> finanzenLeads = finanzenLeadService.getFinanzenLeadsToPushToKlickTipp();
        if (finanzenLeads.size() > 0) {
            klickTippClient.logIn();
            log.info("found {} new finanzen leads to push to KlickTipp.", finanzenLeads.size());
            for (FinanzenLead lead : finanzenLeads) {
                log.info("pushing finanzen lead with id {} to KlickTipp", lead.getId());
                klickTippClient.pushFinanzenLead(lead);
            }
            klickTippClient.logOut();

        }

        List<EgenticLead> egenticLeads = egenticLeadService.getEgenticLeadsToPushToKlickTipp();
        if (egenticLeads.size() > 0) {
            klickTippClient.logIn();
            log.info("found {} new egentic leads to push to KlickTipp.", egenticLeads.size());
            for (EgenticLead lead : egenticLeads) {
                log.info("pushing egentic lead with id {} to KlickTipp", lead.getId());
                klickTippClient.pushEgenticLead(lead);
            }
            klickTippClient.logOut();
        }

    }


}
