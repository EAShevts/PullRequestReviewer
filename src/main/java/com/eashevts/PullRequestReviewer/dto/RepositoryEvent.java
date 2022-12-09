package com.eashevts.PullRequestReviewer.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class RepositoryEvent {
    String name;
    List<Event> events;
    String pathToEvent;


    public RepositoryEvent(
            @JsonProperty("name")
            String name,
            @JsonProperty("events")
            List<Event> events,
            @JsonProperty("pathToEvent")
            String pathToEvent) {
        this.name = name;
        this.events = events;
        this.pathToEvent = pathToEvent;
    }
}
