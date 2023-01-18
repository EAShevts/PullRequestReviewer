package com.eashevts.PullRequestReviewer.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface DataService {
    public String getValue(Object value, String jsonPath);
    public JsonNode getParameterFromValue(Object value, String jsonPath);
    public JsonNode convertToJsonNode(Object value);
}
