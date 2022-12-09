package com.eashevts.PullRequestReviewer.service;

import com.eashevts.PullRequestReviewer.rest.dto.request.SourceRequest;
import okhttp3.Response;

import java.util.List;

public interface HttpService {

    public List<Response> executeActions(List<String> actions, SourceRequest request);
}
