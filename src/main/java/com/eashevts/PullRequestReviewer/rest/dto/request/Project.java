package com.eashevts.PullRequestReviewer.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project{
    public String key;
    public int id;
    public String name;
    @JsonProperty("public")
    public boolean isPublic;
    public String type;
}
