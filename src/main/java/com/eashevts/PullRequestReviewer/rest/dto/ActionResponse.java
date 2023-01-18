package com.eashevts.PullRequestReviewer.rest.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionResponse {
    int statusCode;
    String body;
    String message = "";
    String actionName = "";
}
