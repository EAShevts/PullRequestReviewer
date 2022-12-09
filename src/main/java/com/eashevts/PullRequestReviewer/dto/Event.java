package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class Event {
    String event;
    List<Rules> rules;

    public Event(@JsonProperty("event")
                 String event,
                 @JsonProperty("rules")
                 List<Rules> rules) {
        this.event = event;
        this.rules = rules;
    }

}
