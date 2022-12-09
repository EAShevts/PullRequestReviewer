package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Condition {
    String jsonPath;
    String value;

    public Condition(@JsonProperty("jsonPath") String jsonPath, @JsonProperty("value") String value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }
}
