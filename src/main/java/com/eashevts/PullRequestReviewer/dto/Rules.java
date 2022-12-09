package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class Rules {
    private List<Condition> conditions;
    private String action;

    public Rules(@JsonProperty("action") String action, @JsonProperty("conditions") List<Condition> conditions) {
        this.conditions = conditions;
        this.action = action;
    }
}
