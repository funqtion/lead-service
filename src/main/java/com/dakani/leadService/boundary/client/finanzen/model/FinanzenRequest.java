package com.dakani.leadService.boundary.client.finanzen.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FinanzenRequest {

    private final String id = "1";
    private final String jsonrpc = "2.0";
    private final String method = "lead.getList" ;
    private HashMap<String, HashMap> params;

    public FinanzenRequest(String identifier, String secret, String soldAtMin, int limit, int offset ) {
        this.params = buildParams(identifier, secret, soldAtMin, limit, offset);
    }

    private static HashMap<String, HashMap> buildParams(String identifier, String secret, String soldAtMin, int limit, int offset) {
        HashMap<String, HashMap> params = new HashMap<>();
        HashMap<String, HashMap> request = new HashMap<>();
        HashMap<String, String> credential = new HashMap<>();
        HashMap<String, String> filter = new HashMap<>();
        filter.put("startDate", "0000-00-00");
        filter.put("soldAtMin", soldAtMin);
        filter.put("limit", String.valueOf(limit));
        filter.put("offset", String.valueOf(offset));
        credential.put("identifier", identifier);
        credential.put("secret", secret);

        request.put("credential", credential);
        request.put("filter", filter);
        params.put("request", request);

        return params;
    }


}
