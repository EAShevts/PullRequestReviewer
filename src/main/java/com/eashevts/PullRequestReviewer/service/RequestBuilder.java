package com.eashevts.PullRequestReviewer.service;

import com.eashevts.PullRequestReviewer.dto.Action;
import okhttp3.Request;

public interface RequestBuilder {

    Request buildRequest(Action action);
}
