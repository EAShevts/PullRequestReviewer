package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.dto.Condition;
import com.eashevts.PullRequestReviewer.dto.Event;
import com.eashevts.PullRequestReviewer.dto.RepositoryEvent;
import com.eashevts.PullRequestReviewer.dto.Rule;
import com.eashevts.PullRequestReviewer.rest.dto.ActionResponse;
import com.eashevts.PullRequestReviewer.rest.dto.ExecutableAction;
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

        List<ActionResponse> result = new ArrayList<>();

        events.stream().forEach(
                event -> {
                    ExecutableAction executableAction = generateExecuteActions(event, request);
                    result.contains(executeAction(executableAction));
                }
        );
        return null;
    }

    private List<ActionResponse> executeAction(ExecutableAction executableAction) {
        List<ActionResponse> result = new ArrayList<>();
        executableAction.getRules().forEach(
                rule -> {
                    ActionResponse actionResponse = httpService.executeAction(executableAction.getPlaceholders(), rule.getAction());

                    executableAction.getPlaceholders().put(rule.getSavedName(),
                            dataService.convertToJsonNode(actionResponse.getBody()));
                    result.add(actionResponse);
                }
        );
        return result;
    }

    private ExecutableAction generateExecuteActions(Event event, JsonNode request) {

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("Parameters",
                dataService.getParameterFromValue(request, event.getPath2parameters()));
        parameter.put("Request", request);

        return ExecutableAction.builder()
                .rules(computeEvent(event, request))
                .placeholders(parameter)
                .build();
    }


    private List<Rule> computeEvent(Event events, JsonNode request) {
        List<Rule> rules = events.getRules();
        return rules.stream()
                .filter(r -> checkAllConditions(r.getConditions(), request))
                .collect(Collectors.toList());

    }

    private boolean checkAllConditions(List<Condition> conditions, JsonNode request) {
        return !conditions.stream()
                .anyMatch(condition -> !Objects.equals(dataService.getValue(request, condition.getJsonPath()), condition.getValue()));
    }

}
