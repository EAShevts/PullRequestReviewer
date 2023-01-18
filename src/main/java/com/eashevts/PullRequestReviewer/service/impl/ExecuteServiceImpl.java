package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.dto.Condition;
import com.eashevts.PullRequestReviewer.dto.Event;
import com.eashevts.PullRequestReviewer.dto.RepositoryEvent;
import com.eashevts.PullRequestReviewer.dto.Rules;
import com.eashevts.PullRequestReviewer.rest.dto.ActionResponse;
import com.eashevts.PullRequestReviewer.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ExecuteServiceImpl implements ExecuteService {
    HttpService httpService;
    ProjectConfigurationService projectConfigurationService;
    RequestBuilder requestBuilder;
    DataService dataService;


    @Override
    public ResponseBody processEvent(JsonNode request, String inputType, Long repositoryId) {
        RepositoryEvent repositoryEvent = projectConfigurationService.getRepositoryConfig(repositoryId, inputType);
        String inputEventValue = dataService.getValue(request, repositoryEvent.getPathToEvent());

        List<Event> events = repositoryEvent.getEvents().stream()
                .filter(v -> Objects.equals(v.getEvent(), inputEventValue))
                .collect(Collectors.toList());

        List<Rules> rules = computeEvents(events, request);

        List<ActionResponse> result = new ArrayList<>();

        rules.forEach(
                rule -> {
                    Map<String, Object> templateValue = new HashMap<>();
                    putValueForTemplate(templateValue, request, "Request");
                    putValueForTemplate(templateValue, dataService.getParameterFromValue(request, "comment.text"), "Parameters");
                    if (! Objects.equals(rule.getEnrichAction(), null))
                    {
                        ActionResponse enrichResponse =  httpService.executeAction(templateValue, rule.getEnrichAction());
                        log.info("Execute enrich action {}, response {}", rule.getEnrichAction(), enrichResponse);
                        putValueForTemplate(templateValue, dataService.convertToJsonNode(enrichResponse.getBody()), "Enrich");
                    }

                    result.add(httpService.executeAction(templateValue, rule.getAction()));
                }

        );
        return null;
    }

    private Map<String, Object> putValueForTemplate(Map<String, Object> result, Object value, String name) {
        result.put(name, value);
        return result;
    }

    private List<Rules> computeEvents(List<Event> events, JsonNode request) {
        List<Rules> rules = events.stream().flatMap(e -> e.getRules().stream()).collect(Collectors.toList());
        return rules.stream()
                .filter(r -> checkAllConditions(r.getConditions(), request))
                .collect(Collectors.toList());

    }

    private boolean checkAllConditions(List<Condition> conditions, JsonNode request) {
        return !conditions.stream()
                .anyMatch(condition -> !Objects.equals(dataService.getValue(request, condition.getJsonPath()), condition.getValue()));
    }

}
