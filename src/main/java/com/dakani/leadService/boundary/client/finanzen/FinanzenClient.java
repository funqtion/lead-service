package com.dakani.leadService.boundary.client.finanzen;

import com.dakani.leadService.boundary.client.finanzen.model.FinanzenError;
import com.dakani.leadService.boundary.client.finanzen.model.FinanzenLeadDto;
import com.dakani.leadService.boundary.client.finanzen.model.FinanzenRequest;
import com.dakani.leadService.boundary.client.finanzen.model.FinanzenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Either;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@Slf4j(topic = "leadservice")
public class FinanzenClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final HttpHeaders headers = new HttpHeaders();
    @Value("${finanzen.identifier}")
    private String identifier;
    @Value("${finanzen.secret}")
    private String secret;
    @Value("${finanzen.baseUrl}")
    private String baseUrl;

    public FinanzenClient() {
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
        this.headers.setContentType(MediaType.APPLICATION_JSON);

    }

    public List<FinanzenLeadDto> pullLastDay() {
        return getFinanzenLeadDtos(LocalDateTime.now(ZoneId.of("Europe/Berlin")).truncatedTo(ChronoUnit.MILLIS).minusDays(1).toString());
    }
    public List<FinanzenLeadDto> pullLastYear() {
        return getFinanzenLeadDtos(LocalDateTime.now(ZoneId.of("Europe/Berlin")).truncatedTo(ChronoUnit.MILLIS).minusYears(1).toString());
    }

    private List<FinanzenLeadDto> getFinanzenLeadDtos(String yesterday) {
        FinanzenResponse finanzenResponse = new FinanzenResponse();
        int offset = 0;
        int limit = 50;
        boolean done = false;

        while (!done) {
            Either<FinanzenError, FinanzenResponse> loopLeads = this.getFinanzenLeads(limit, offset, yesterday);
            if (loopLeads.isLeft()) {
                log.warn("Failed to get leads from Finanzen.de with this error {}", loopLeads.getLeft().getMessage());
                done = true;
            } else {
                finanzenResponse.combineLeads(loopLeads.get());
            }

            if (finanzenResponse.getItems().size() == finanzenResponse.getCount()) done = true;
            else offset += 50;
        }

        return finanzenResponse.getItems().stream().map(FinanzenLeadDto::new).collect(Collectors.toList());
    }


    public Either<FinanzenError,FinanzenResponse> getFinanzenLeads(int limit, int offset, String dateFrom) {


        FinanzenRequest finanzenRequest = new FinanzenRequest(this.identifier, this.secret, dateFrom,limit, offset);

        String requestBody = getRequestBody(finanzenRequest);

        HttpEntity<String> request = new HttpEntity<String>(requestBody, this.headers);


        ResponseEntity<String> response = getResponseEntity(request);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                JsonNode leadJson = this.mapper.readTree(response.getBody());
                if (leadJson.has("error")) {
                    JsonNode error = leadJson.get("error");
                    return Either.left(this.mapper.treeToValue(error, FinanzenError.class));
                } else {
                    return Either.right(mapper.treeToValue(leadJson.get("result").get("list"), FinanzenResponse.class));
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Either.left(new FinanzenError(0, "Unknown leadService Error", response.toString()));
    }

    private ResponseEntity<String> getResponseEntity(HttpEntity<String> request) {
        ResponseEntity<String> response;
        try {
            response = this.restTemplate.postForEntity(this.baseUrl, request, String.class);

        } catch(HttpStatusCodeException e) {
            response = ResponseEntity.status(e.getStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
        return response;
    }

    private String getRequestBody(FinanzenRequest finanzenRequest) {
        String requestBody = null;

        try {
            requestBody = this.mapper.writeValueAsString(finanzenRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestBody;
    }

}
