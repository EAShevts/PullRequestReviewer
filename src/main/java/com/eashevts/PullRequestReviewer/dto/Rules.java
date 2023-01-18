package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.List;

@Value
public class Rules {
    private String enrichAction;
    private List<Condition> conditions;
    private String action;

    public Rules(@Nullable @JsonProperty("enrichAction") String enrichAction,
                 @JsonProperty("action") String action,
                 @JsonProperty("conditions") List<Condition> conditions) {
        this.enrichAction = enrichAction;
        this.conditions = conditions;
        this.action = action;
    }
}
