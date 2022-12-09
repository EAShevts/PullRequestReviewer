package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.dto.*;
import com.eashevts.PullRequestReviewer.rest.dto.request.SourceRequest;
import com.eashevts.PullRequestReviewer.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
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
    public ResponseBody processEvent(SourceRequest request, String inputType, Long repositoryId) {
        RepositoryEvent repositoryEvent = projectConfigurationService.getRepositoryConfig(repositoryId, inputType);
        String inputEventValue = dataService.getValueByJsonPath(request, repositoryEvent.getPathToEvent());

        List<Event> events = repositoryEvent.getEvents().stream()
                .filter(v -> Objects.equals(v.getEvent(), inputEventValue))
                .collect(Collectors.toList());

        List<String> actions = computeEvents(events, request);
        List<Response> result =  httpService.executeActions(actions, request);


//        RepositoryEvent event = currentProject.getRepositoryEvent().stream()
//                .filter(v -> Objects.equals(v.getEvent(), request.getEventKey()))
//                .findFirst()
////                .orElseGet(null);
//        log.info("For request {} find next settings, event: {}, configuration {}",request ,event , currentProject);
//        List<String> executableActions = new ArrayList<>();
//        event.getRules().forEach(
//                rule -> {
//                    String value = dataService.getValueByJsonPath(request, rule.getJsonPath());
//                    log.info("Execute condition  {} = {}", value , rule.getValue() );
//                    if (value.equals(rule.getValue())){
//                        executableActions.add(rule.getAction());
//                    }
//                    log.info("For request {}, need execute next actions {}", request, executableActions );
//                }
//        );
//       List<Response> result =  httpService.executeActions(executableActions);
        return null;
    }

    private List<String> computeEvents(List<Event> events, SourceRequest request) {
        List<Rules> rules = events.stream().flatMap(e -> e.getRules().stream()).collect(Collectors.toList());
        return  rules.stream()
                .filter(r -> checkAllConditions(r.getConditions(), request))
                .map(r -> r.getAction()).collect(Collectors.toList());

    }

    private boolean checkAllConditions(List<Condition> conditions, SourceRequest request) {
        return !conditions.stream()
                .anyMatch(condition -> !Objects.equals(dataService.getValueByJsonPath(request, condition.getJsonPath()), condition.getValue()));
    }
//    @Override
//    private  parseEvent(){
//
//    }

}
