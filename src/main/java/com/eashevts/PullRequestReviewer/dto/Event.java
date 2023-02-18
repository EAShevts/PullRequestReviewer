package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Event {
    String event;
    String path2parameters;
    List<Rule> rules;

    public Event(@JsonProperty("event")
                 String event,
                 @JsonProperty("pathToParameters")
                 String path2parameters,
                 @JsonProperty("rules")
                 List<Rule> rules) {
        this.event = event;
        this.path2parameters = path2parameters;
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(Rule::getPriority).reversed())
                .collect(Collectors.toList());
    }

}
