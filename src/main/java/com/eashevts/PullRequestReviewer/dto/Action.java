package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Action {
    String name;
    String url;
    String method;
    String body;

    public Action(
            @JsonProperty("name")
            String name,
            @JsonProperty("url")
            String url,
            @JsonProperty("method")
            String method,
            @JsonProperty("body")
            String body) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.body = body;
    }
}
