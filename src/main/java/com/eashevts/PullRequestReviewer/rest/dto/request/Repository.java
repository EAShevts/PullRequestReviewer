package com.eashevts.PullRequestReviewer.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository{
    public String slug;
    public int id;
    public String name;
    public String scmId;
    public String state;
    public String statusMessage;
    public boolean forkable;
    public Project project;
    @JsonProperty("public")
    public boolean isPublic;
}
