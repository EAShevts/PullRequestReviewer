package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.configuration.action.ActionLoader;
import com.eashevts.PullRequestReviewer.dto.Action;
import com.eashevts.PullRequestReviewer.dto.ActionHeaders;
import com.eashevts.PullRequestReviewer.rest.dto.ActionResponse;
import com.eashevts.PullRequestReviewer.service.DataService;
import com.eashevts.PullRequestReviewer.service.HttpService;
import com.eashevts.PullRequestReviewer.service.TemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class HttpServiceImpl implements HttpService {
    List<Action> allActions;
    OkHttpClient client = new OkHttpClient();
    TemplateService templateService;
    DataService dataService;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    @Autowired
    public HttpServiceImpl(ActionLoader loader, TemplateService templateService) {
        this.templateService = templateService;
        allActions = loader.loadAction();
    }

    @Override
    public List<ActionResponse> executeActions(List<String> actions, Map<String, Object> parametersForTemplate) {
        return allActions.stream()
                .filter(v -> actions.contains(v.getName()))
                .map(action -> execute(action, parametersForTemplate))
                .collect(Collectors.toList());
    }

    @Override
    public ActionResponse executeAction(Map<String, Object> parametersForTemplate, String action) {
        return execute(allActions.stream()
                .filter(v -> Objects.equals(v.getName(), action))
                .findFirst()
                .get(), parametersForTemplate);

    }


    private ActionResponse execute(Action action, Map<String, Object> parametersForTemplate) {
        RequestBody requestBody = RequestBody.create(buildBody(parametersForTemplate, action.getName()), JSON);

        Request request = new Request.Builder()
                .url(templateService.processTemplateURL(parametersForTemplate, action.getName()))
                .headers(buildHeaders(action))
                .method(action.getMethod(), requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            log.info("Req {} response {}", request, response);
            return ActionResponse.builder()
                    .body(response.body().string())
                    .statusCode(response.code())
                    .message(response.message())
                    .actionName(action.getName())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Headers buildHeaders(Action action) {
        Map<String, String> headers = action.getHeaders().stream().collect(Collectors.toMap(ActionHeaders::getName, ActionHeaders::getValue));
        return Headers.of(headers);
    }

    private String buildBody( Map<String, Object> placeholders, String actionName) {
        return templateService.processTemplateBodyAction(placeholders, actionName);
    }

}
