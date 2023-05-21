package com.dakani.leadService.boundary.client.finanzen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class FinanzenResponse {
    private int count;
    private int offset;
    private List<Map> items;

    public FinanzenResponse() {
        this.offset = 0;
        this.count = 0;
        this.items = Collections.emptyList();
     }

    public void combineLeads(FinanzenResponse finanzenResponse) {
        this.setCount(finanzenResponse.getCount());
        this.setOffset(finanzenResponse.getOffset());
        this.setItems(Stream.concat(this.items.stream(), finanzenResponse.getItems().stream()).toList());

    }
}
