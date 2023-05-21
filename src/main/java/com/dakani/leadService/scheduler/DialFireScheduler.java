package com.dakani.leadService.scheduler;


import com.dakani.leadService.boundary.client.dialFire.DialFireClient;
import com.dakani.leadService.persistence.entity.*;
import com.dakani.leadService.service.EgenticLeadService;
import com.dakani.leadService.service.FinanzenLeadService;
import com.dakani.leadService.service.FunnelLeadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j(topic = "leadservice")
public class DialFireScheduler {

    private final EgenticLeadService egenticLeadService;
    private final FinanzenLeadService finanzenLeadService;
    private final FunnelLeadService funnelLeadService;
    private final DialFireClient dialFireClient;

    @Scheduled(cron = "${scheduler.dialFire.cron}")
    public void postNewLeadsToDialFire() {
        List<EgenticLead> egenticLeads = egenticLeadService.getEgenticLeadsToPushToDialFire();
        if (egenticLeads.size() > 0) {
            log.info("found {} new egentic leads to push to DialFire.", egenticLeads.size());
            for (EgenticLead lead : egenticLeads) {
                log.info("pushing egentic lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewEgenticLead(lead, "egentic");
            }
        }

        List<FinanzenLead> finanzenLeads = finanzenLeadService.getFinanzenLeadsToPushToDialFire();
        if (finanzenLeads.size() > 0) {
            log.info("found {} new finanzen leads to push to DialFire.", finanzenLeads.size());
            for (FinanzenLead lead : finanzenLeads) {
                log.info("pushing finanzen lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewFinanzenLead(lead, "finanzen");
            }
        }

        List<AnimalLead> animalLeads = funnelLeadService.getAnimalLeadsToPushToDialFire();
        if (animalLeads.size() > 0) {
            log.info("found {} new animal leads to push to DialFire.", animalLeads.size());
            for (AnimalLead lead : animalLeads) {
                log.info("pushing animal lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewAnimalLead(lead, "animal");
            }
        }

        List<TeethLead> teethLeads = funnelLeadService.getTeethLeadsToPushToDialFire();
        if (teethLeads.size() > 0) {
            log.info("found {} new teeth leads to push to DialFire.", teethLeads.size());
            for (TeethLead lead : teethLeads) {
                log.info("pushing teeth lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewTeethLead(lead, "teeth");
            }
        }

        List<HouseLead> houseLeads = funnelLeadService.getHouseLeadsToPushToDialFire();
        if (houseLeads.size() > 0) {
            log.info("found {} new house leads to push to DialFire.", houseLeads.size());
            for (HouseLead lead : houseLeads) {
                log.info("pushing house lead with id {} to DialFire", lead.getId());
                dialFireClient.pushNewHouseLead(lead, "house");
            }
        }
    }
}