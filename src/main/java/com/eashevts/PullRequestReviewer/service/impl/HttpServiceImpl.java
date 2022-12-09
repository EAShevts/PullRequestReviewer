package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.configuration.action.ActionLoader;
import com.eashevts.PullRequestReviewer.dto.Action;
import com.eashevts.PullRequestReviewer.rest.dto.request.SourceRequest;
import com.eashevts.PullRequestReviewer.service.HttpService;
import com.eashevts.PullRequestReviewer.service.TemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class HttpServiceImpl implements HttpService {
    List<Action> allActions;
    OkHttpClient client = new OkHttpClient();
    TemplateService templateService;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    @Autowired
    public HttpServiceImpl(ActionLoader loader, TemplateService templateService) {
        this.templateService = templateService;
        allActions = loader.loadAction();
    }

    @Override
    public List<Response> executeActions(List<String> actions, SourceRequest request) {
        return allActions.stream()
                .filter(v -> actions.contains(v.getName()))
                .map(action -> execute(action, request))
                .collect(Collectors.toList());
    }


    private Response execute(Action action, SourceRequest sourceRequest) {
        RequestBody requestBody = RequestBody.create(buildBody(sourceRequest, action.getName()), JSON);

        Request request = new Request.Builder()
                .url(action.getUrl())
                .method(action.getMethod(), requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            log.info("Req {} response {}", request, response);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildBody(SourceRequest sourceRequest, String actionName) {
        Map<String, Object> valueForTemplate = Collections.singletonMap("Request", sourceRequest);
        return templateService.processTemplate(valueForTemplate, actionName);
    }

}
