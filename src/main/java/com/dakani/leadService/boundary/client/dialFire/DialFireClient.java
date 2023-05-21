package com.dakani.leadService.boundary.client.dialFire;


import com.dakani.leadService.boundary.client.dialFire.model.DialFireEgenticRequestBody;
import com.dakani.leadService.boundary.client.dialFire.model.DialFireFinanzenRequestBody;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.FinanzenLead;
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
    @Value("${dialFire.egenticCampaignToken}")
    private String egenticCampaignToken;
    @Value("${dialFire.finanzenCampaignToken}")
    private String finanzenCampaignToken;
    @Value("${dialFire.collectorCampaignId}")
    private String collectorCampaignId;
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
        if (campaign.equals("egentic")) {
            this.headers.setBearerAuth(this.egenticCampaignToken);

            url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.egenticCampaignId, this.taskIdAnrufen);

        } else if (campaign.equals("finanzen")) {
            this.headers.setBearerAuth(this.finanzenCampaignToken);

            url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.finanzenCampaignId, this.taskIdAnrufen);

        } else {
            this.headers.setBearerAuth(this.collectorCampaignToken);

            url = this.baseUrl + String.format("/api/campaigns/%s/tasks/%s/contacts/create", this.collectorCampaignId, this.taskIdAnrufen);
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
