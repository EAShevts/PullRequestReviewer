package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.List;

@Value
public class Action {
    String name;
    String url;
    @Nullable
    List<ActionHeaders> headers;
    String method;
    String body;

    public Action(
            @JsonProperty("name")
            String name,
            @JsonProperty("url")
            String url,
            @Nullable @JsonProperty("headers")
            List<ActionHeaders> headers,
            @JsonProperty("method")
            String method,
            @JsonProperty("body")
            String body) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.body = body;
        this.headers = headers;
    }
}
