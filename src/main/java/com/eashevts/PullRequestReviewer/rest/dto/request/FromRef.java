package com.eashevts.PullRequestReviewer.rest.dto.request;

 import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
 import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FromRef{
    public String id;
    public String displayId;
    public String latestCommit;
    public Repository repository;
}






