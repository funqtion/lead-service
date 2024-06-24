package com.dakani.leadService.scheduler;


import com.dakani.leadService.boundary.client.klickTipp.KlickTippClient;
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
public class KlickTippScheduler {

    private final EgenticLeadService egenticLeadService;
    private final FinanzenLeadService finanzenLeadService;
    private final KlickTippClient klickTippClient;
    private final FunnelLeadService funnelLeadService;

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

//        List<AnimalLead> animalLeads = funnelLeadService.getAnimalLeadsToPushToKlickTipp();
//        if (animalLeads.size() > 0) {
//            klickTippClient.logIn();
//            log.info("found {} new animal leads to push to KlickTipp.", animalLeads.size());
//            for (AnimalLead lead : animalLeads) {
//                log.info("pushing animal lead with id {} to KlickTipp", lead.getId());
//                klickTippClient.pushAnimalFunnelLead(lead);
//            }
//            klickTippClient.logOut();
//        }
//
//        List<TeethLead> teethLeads = funnelLeadService.getTeethLeadsToPushToKlickTipp();
//        if (teethLeads.size() > 0) {
//            klickTippClient.logIn();
//            log.info("found {} new teeth leads to push to KlickTipp.", teethLeads.size());
//            for (TeethLead lead : teethLeads) {
//                log.info("pushing teeth lead with id {} to KlickTipp", lead.getId());
//                klickTippClient.pushTeethFunnelLead(lead);
//            }
//            klickTippClient.logOut();
//        }

        List<HouseLead> houseLeads = funnelLeadService.getHouseLeadsToPushToKlickTipp();
        log.info("found {} new house leads to push to KlickTipp.", houseLeads.size());

        if (houseLeads.size() > 0) {
            klickTippClient.logIn();
            log.info("found {} new house leads to push to KlickTipp.", houseLeads.size());
            for (HouseLead lead : houseLeads) {
                log.info("pushing house lead with id {} to KlickTipp", lead.getId());
                klickTippClient.pushHouseFunnelLead(lead);
            }
            klickTippClient.logOut();
        }
    }
}
