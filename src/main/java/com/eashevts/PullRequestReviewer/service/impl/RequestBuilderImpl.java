package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.dto.action.Action;
import com.eashevts.PullRequestReviewer.service.RequestBuilder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

@Service
public class RequestBuilderImpl implements RequestBuilder {
    @Override
    public Request buildRequest(Action action) {

        return new Request.Builder().url(action.getUrl()).method(action.getMethod(),
                        RequestBody.create(MediaType.parse("application/json"), action.getBody()))
                .build();
    }
}
