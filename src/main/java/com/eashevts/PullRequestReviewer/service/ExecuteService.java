package com.eashevts.PullRequestReviewer.service;

import com.eashevts.PullRequestReviewer.rest.dto.request.SourceRequest;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ExecuteService {

    public ResponseBody processEvent(SourceRequest request, String inputType, Long repositoryId);
}
