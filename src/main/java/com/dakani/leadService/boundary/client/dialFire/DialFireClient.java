package com.dakani.leadService.boundary.client.dialFire;


import com.dakani.leadService.boundary.client.dialFire.model.*;
import com.dakani.leadService.persistence.entity.*;
import com.dakani.leadService.service.LeadMetaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Getter
@Service
@Slf4j(topic = "leadservice")
public class DialFireClient {
    private final LeadMetaService leadMetaService;
    private final HttpHeaders headers = new HttpHeaders();
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    @Value("${dialFire.baseUrl}")
    private String baseUrl;
    @Value("${dialFire.egenticCampaignId}")
    private String egenticCampaignId;
    @Value("${dialFire.finanzenCampaignId}")
    private String finanzenCampaignId;
    @Value("${dialFire.animalCampaignId}")
    private String animalCampaignId;
    @Value("${dialFire.teethCampaignId}")
    private String teethCampaignId;
    @Value("${dialFire.houseCampaignId}")
    private String houseCampaignId;
    @Value("${dialFire.collectorCampaignId}")
    private String collectorCampaignId;
    @Value("${dialFire.egenticCampaignToken}")
    private String egenticCampaignToken;
    @Value("${dialFire.finanzenCampaignToken}")
    private String finanzenCampaignToken;
    @Value("${dialFire.animalCampaignToken}")
    private String animalCampaignToken;
    @Value("${dialFire.teethCampaignToken}")
    private String teethCampaignToken;
    @Value("${dialFire.houseCampaignToken}")
    private String houseCampaignToken;
    @Value("${dialFire.collectorCampaignToken}")
    private String collectorCampaignToken;
    @Value("${dialFire.taskIdAnrufen}")
    private String taskIdAnrufen;

    public DialFireClient(LeadMetaService leadMetaService) {
        this.leadMetaService = leadMetaService;
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }


    public void pushNewAnimalLead(AnimalLead animalLead, String campaign) {
        DialFireAnimalRequestBody dialFireAnimalRequestBody = new DialFireAnimalRequestBody(animalLead);

        String url = generateUrl(campaign);

        String requestBodyJson = generateRequestBodyInJson(dialFireAnimalRequestBody);

        HttpEntity<String> request = new HttpEntity<String>(requestBodyJson, this.headers);

        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("successfully pushed animal lead with id {} to DialFire", animalLead.getId());
            handleSuccessfulRequest("animal", animalLead.getId());
        } else {
            log.warn("failed to push animal lead with id {} to DialFire. encountered error {}", animalLead.getId(), response.getBody());
            handleFailedRequest("animal", animalLead.getId());
        }
    }

    public void pushNewTeethLead(TeethLead teethLead, String campaign) {
        DialFireTeethRequestBody dialFireTeethRequestBody = new DialFireTeethRequestBody(teethLead);

        String url = generateUrl(campaign);

        String requestBodyJson = generateRequestBodyInJson(dialFireTeethRequestBody);

        HttpEntity<String> request = new HttpEntity<String>(requestBodyJson, this.headers);

        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("successfully pushed teeth lead with id {} to DialFire", teethLead.getId());
            handleSuccessfulRequest("teeth", teethLead.getId());
        } else {
            log.warn("failed to push teeth lead with id {} to DialFire. encountered error {}", teethLead.getId(), response.getBody());
            handleFailedRequest("teeth", teethLead.getId());
        }
    }

    public void pushNewHouseLead(HouseLead houseLead, String campaign) {
        DialFireHouseRequestBody dialFireHouseRequestBody = new DialFireHouseRequestBody(houseLead);

        String url = generateUrl(campaign);

        String requestBodyJson = generateRequestBodyInJson(dialFireHouseRequestBody);

        HttpEntity<String> request = new HttpEntity<String>(requestBodyJson, this.headers);

        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("successfully pushed house lead with id {} to DialFire", houseLead.getId());
            handleSuccessfulRequest("house", houseLead.getId());
        } else {
            log.warn("failed to push house lead with id {} to DialFire. encountered error {}", houseLead.getId(), response.getBody());
            handleFailedRequest("house", houseLead.getId());
        }
    }
    public void pushNewFinanzenLead(FinanzenLead finanzenLead, String campaign) {
        DialFireFinanzenRequestBody dialFireFinanzenRequestBody = new DialFireFinanzenRequestBody(finanzenLead);

        String url = generateUrl(campaign);

        String requestBodyJson = generateRequestBodyInJson(dialFireFinanzenRequestBody);

        HttpEntity<String> request = new HttpEntity<String>(requestBodyJson, this.headers);


        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("successfully pushed finanzen lead with id {} to DialFire", finanzenLead.getId());
            handleSuccessfulRequest("finanzen", finanzenLead.getId());
        } else {
            log.warn("failed to push finanzen lead with id {} to DialFire. encountered error {}", finanzenLead.getId(), response.getBody());
            handleFailedRequest("finanzen", finanzenLead.getId());
        }
    }

    public void pushNewEgenticLead(EgenticLead egenticLead, String campaign) {

        DialFireEgenticRequestBody dialFireEgenticRequestBody = new DialFireEgenticRequestBody(egenticLead);

        String url = generateUrl(campaign);

        String requestBodyJson = generateRequestBodyInJson(dialFireEgenticRequestBody);

        HttpEntity<String> request = new HttpEntity<String>(requestBodyJson, this.headers);


        ResponseEntity<String> response = postForEntity(url, request);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("successfully pushed egentic lead with id {} to DialFire", egenticLead.getId());
            handleSuccessfulRequest("egentic", egenticLead.getId());
        } else {
            log.warn("failed to push egentic lead with id {} to DialFire. encountered error {}", egenticLead.getId(), response.getBody());
            handleFailedRequest("egentic", egenticLead.getId());
        }

    }

    private ResponseEntity<String> postForEntity(String url, HttpEntity<String> request) {
        ResponseEntity<String> response;
        try {
            response = this.restTemplate.postForEntity(url, request, String.class);

        } catch (HttpStatusCodeException e) {
            response = ResponseEntity.status(e.getStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
        return response;
    }

    private String generateRequestBodyInJson(DialFireAnimalRequestBody dialFireAnimalRequestBody) {
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(dialFireAnimalRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBodyInJson;
    }

    private String generateRequestBodyInJson(DialFireTeethRequestBody dialFireTeethRequestBody) {
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(dialFireTeethRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBodyInJson;
    }

    private String generateRequestBodyInJson(DialFireHouseRequestBody dialFireHouseRequestBody) {
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(dialFireHouseRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBodyInJson;
    }

    private String generateRequestBodyInJson(DialFireEgenticRequestBody dialFireEgenticRequestBody) {
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(dialFireEgenticRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBodyInJson;
    }

    private String generateRequestBodyInJson(DialFireFinanzenRequestBody dialFireFinanzenRequestBody) {
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(dialFireFinanzenRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBodyInJson;
    }

    private String generateUrl(String campaign) {

        String url;
        switch (campaign) {
            case "egentic" -> {
                this.headers.setBearerAuth(this.egenticCampaignToken);
                url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.egenticCampaignId, this.taskIdAnrufen);
            }
            case "finanzen" -> {
                this.headers.setBearerAuth(this.finanzenCampaignToken);
                url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.finanzenCampaignId, this.taskIdAnrufen);
            }
            case "animal" -> {
                this.headers.setBearerAuth(this.animalCampaignToken);
                url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.animalCampaignId, this.taskIdAnrufen);
            }
            case "teeth" -> {
                this.headers.setBearerAuth(this.teethCampaignToken);
                url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.teethCampaignId, this.taskIdAnrufen);
            }
            case "house" -> {
                this.headers.setBearerAuth(this.houseCampaignToken);
                url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.houseCampaignId, this.taskIdAnrufen);
            }
            default -> {
                this.headers.setBearerAuth(this.collectorCampaignToken);
                url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.collectorCampaignId, this.taskIdAnrufen);
            }
        }
        return url;
    }

    public void handleSuccessfulRequest(String type, long id) {
        leadMetaService.updateLeadStateDialFire(type, id, true);
    }

    public void handleFailedRequest(String type, long id) {
        leadMetaService.updateLeadStateDialFire(type, id, false);

    }
}
