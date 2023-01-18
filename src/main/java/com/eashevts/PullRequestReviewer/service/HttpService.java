package com.eashevts.PullRequestReviewer.service;

import com.eashevts.PullRequestReviewer.rest.dto.ActionResponse;

import java.util.List;
import java.util.Map;

public interface HttpService {

    public List<ActionResponse> executeActions(List<String> actions, Map<String, Object> parametersForTemplate);
    public ActionResponse executeAction(Map<String, Object> parametersForTemplate, String action);
}
