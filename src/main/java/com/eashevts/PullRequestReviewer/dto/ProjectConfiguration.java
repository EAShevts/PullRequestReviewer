package com.eashevts.PullRequestReviewer.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class ProjectConfiguration {
    private String name;
    private long repositoryID;
    private List<RepositoryEvent> repositoryEvent;
    private List<String> reviewers;

    public ProjectConfiguration(
            @JsonProperty("name")
            String name,
            @JsonProperty("repositoryId")
            long repositoryID,
            @JsonProperty("repositoryEvent")
            List<RepositoryEvent> repositoryEvent,
            @JsonProperty("reviewers")
            List<String> reviewers) {
        this.name = name;
        this.repositoryID = repositoryID;
        this.repositoryEvent = repositoryEvent;

        this.reviewers = reviewers;
    }
}
