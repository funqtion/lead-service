package com.dakani.leadService.boundary.client.finanzen.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FinanzenError {
    private int code;
    private String message;
    private String data;

    public void setData(JsonNode data) {
        this.data = data.asText();
    }
}
