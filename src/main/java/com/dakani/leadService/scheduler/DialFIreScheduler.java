package com.dakani.leadService.scheduler;


import com.dakani.leadService.boundary.client.dialFire.DialFireClient;
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
public class DialFIreScheduler {

    private final EgenticLeadService egenticLeadService;
    private final FinanzenLeadService finanzenLeadService;
    private final DialFireClient dialFireClient;

    @Scheduled(cron = "${scheduler.dialFire.cron}")
    public void postNewLeadsToDialFire() {
        List<EgenticLead> egenticLeads = egenticLeadService.getEgenticLeadsToPushToDialFire();
        if (egenticLeads.size() > 0) {
            log.info("found {} new egentic leads to push to DialFire.", egenticLeads.size());
            for (EgenticLead lead : egenticLeads) {
                log.info("pushing egentic lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewEgenticLead(lead, "egentic");
//                dialFireClient.pushNewEgenticLead(lead, "collector");
            }
        }

        List<FinanzenLead> finanzenLeads = finanzenLeadService.getFinanzenLeadsToPushToDialFire();
        if (finanzenLeads.size() > 0) {
            log.info("found {} new finanzen leads to push to DialFire.", finanzenLeads.size());
            for (FinanzenLead lead : finanzenLeads) {
                log.info("pushing finanzen lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewFinanzenLead(lead, "finanzen");
//                dialFireClient.pushNewFinanzenLead(lead, "collector");
            }
        }
    }
}
