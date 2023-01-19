package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Event {
    String event;
    List<Rule> rules;

    public Event(@JsonProperty("event")
                 String event,
                 @JsonProperty("rules")
                 List<Rule> rules) {
        this.event = event;
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(Rule::getPriority).reversed())
                .collect(Collectors.toList());
    }

}
