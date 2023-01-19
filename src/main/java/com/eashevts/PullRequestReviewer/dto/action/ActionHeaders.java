package com.eashevts.PullRequestReviewer.dto.action;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ActionHeaders {
    String name;
    String value;

    public ActionHeaders(
            @JsonProperty("name")
            String name,
            @JsonProperty("value")
            String value) {
        this.name = name;
        this.value = value;
    }
}
