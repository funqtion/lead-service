package com.dakani.leadService.boundary.client.klickTipp;

import com.dakani.leadService.boundary.client.klickTipp.model.KlickTippAuth;
import com.dakani.leadService.boundary.client.klickTipp.model.KlickTippEgenticRequestBody;
import com.dakani.leadService.boundary.client.klickTipp.model.KlickTippFinanzenRequestBody;
import com.dakani.leadService.boundary.client.klickTipp.model.KlickTippTags;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.FinanzenLead;
import com.dakani.leadService.service.LeadMetaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
public class KlickTippClient {
    private final LeadMetaService leadMetaService;
    private final HttpHeaders headers = new HttpHeaders();
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    @Value("${klickTipp.userName}")
    private String userName;
    @Value("${klickTipp.password}")
    private String password;
    @Value("${klickTipp.baseUrl}")
    private String baseUrl;
    @Value("${klickTipp.logInUrl}")
    private String logInUrl;
    @Value("${klickTipp.logOutUrl}")
    private String logOutUrl;
    @Value("${klickTipp.subscribeUrl}")
    private String subscribeUrl;
    @Value("${klickTipp.tagUrl}")
    private String tagUrl;
    private String session_name;
    private String session_id;

    public KlickTippClient(LeadMetaService leadMetaService) {
        this.leadMetaService = leadMetaService;
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void pushEgenticLead(EgenticLead egenticLead) {
        KlickTippEgenticRequestBody klickTippEgenticRequestBody = new KlickTippEgenticRequestBody(egenticLead);
        String url = this.baseUrl + this.subscribeUrl;
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(klickTippEgenticRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request = new HttpEntity<String>(requestBodyInJson, this.headers);
        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            KlickTippTags klickTippTags = new KlickTippTags(egenticLead);
            ResponseEntity<String> tagResponseEntity = this.pushTags(klickTippTags);
            if (tagResponseEntity.getStatusCode().is2xxSuccessful()) {
                handleSuccessfulRequest("egentic", egenticLead.getId());
                log.info("successfully pushed egentic lead to KlickTipp");
            } else {
                handleFailedRequest("egentic", egenticLead.getId());
                log.warn("failed to push tags to KlickTipp. encountered error {}", tagResponseEntity.getBody());
            }
        } else {
            handleFailedRequest("egentic", egenticLead.getId());
            log.warn("failed to push egentic lead to KlickTipp. encountered error {}", response.getBody());
        }
    }

    public void pushFinanzenLead(FinanzenLead finanzenLead){
        KlickTippFinanzenRequestBody klickTippFinanzenRequestBody = new KlickTippFinanzenRequestBody(finanzenLead);
        String url = this.baseUrl + this.subscribeUrl;
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(klickTippFinanzenRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request = new HttpEntity<String>(requestBodyInJson, this.headers);

        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            KlickTippTags klickTippTags = new KlickTippTags(finanzenLead);
            ResponseEntity<String> tagResponseEntity = this.pushTags(klickTippTags);
            if (tagResponseEntity.getStatusCode().is2xxSuccessful()) {

                handleSuccessfulRequest("finanzen", finanzenLead.getId());

                log.info("successfully pushed finanzen lead to KlickTipp");
            } else {
                handleFailedRequest("finanzen", finanzenLead.getId());
                log.warn("failed to push tags to KlickTipp. encountered error {}", tagResponseEntity.getBody());
            }
        } else {
            handleFailedRequest("finanzen", finanzenLead.getId());
            log.warn("failed to push finanzen lead to KlickTipp. encountered error {}", response.getBody());
        }
    }

    public ResponseEntity<String> pushTags(KlickTippTags klickTippTags) {
        String url = this.baseUrl + this.tagUrl;
        String requestBodyInJson;
        try {
            requestBodyInJson = this.mapper.writeValueAsString(klickTippTags);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request = new HttpEntity<String>(requestBodyInJson, this.headers);

        return postForEntity(url, request);
    }
    public boolean logIn(){
        String url = this.baseUrl + this.logInUrl;

        KlickTippAuth klickTippAuth = new KlickTippAuth(this.userName, this.password);
        String requestBody;
        try {
            requestBody = this.mapper.writeValueAsString(klickTippAuth);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> request = new HttpEntity<String>(requestBody, this.headers);

        ResponseEntity<String> response = postForEntity(url, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            JsonNode responseNode;
            try {
                responseNode = this.mapper.readTree(response.getBody());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            this.session_id =  responseNode.get("sessid").textValue();
            this.session_name = responseNode.get("session_name").textValue();
            String cookieHeader = this.session_name + "=" + this.session_id;
            this.headers.set("Cookie", cookieHeader);
            return true;
        } else {
            log.warn("failed To log in {}", response);
            return false;
        }
    }

    public boolean logOut(){
        String url = this.baseUrl + this.logOutUrl;

        HttpEntity<String> request = new HttpEntity<String>(this.headers);

        ResponseEntity<String> response = postForEntity(url, request);

        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        } else {
            log.warn("failed To log out {}", response);
            return false;
        }
    }

    private ResponseEntity<String> postForEntity(String url, HttpEntity<String> request) {
        ResponseEntity<String> response;
        try {
            response = this.restTemplate.postForEntity(url, request, String.class);

        } catch(HttpStatusCodeException e) {
            response = ResponseEntity.status(e.getStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
        return response;
    }

    public void handleSuccessfulRequest(String type, long id) {

        leadMetaService.updateLeadStateKlickTipp(type, id, true);
    }

    public void handleFailedRequest(String type, long id) {
        leadMetaService.updateLeadStateKlickTipp(type, id, false);

    }
}
