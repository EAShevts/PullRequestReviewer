package com.eashevts.PullRequestReviewer.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ExecuteService {

    public ResponseBody processEvent(JsonNode request, String inputType, Long repositoryId);
}
