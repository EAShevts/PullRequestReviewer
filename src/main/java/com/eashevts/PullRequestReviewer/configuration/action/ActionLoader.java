package com.eashevts.PullRequestReviewer.configuration.action;

import com.eashevts.PullRequestReviewer.configuration.FilesConfiguration;
import com.eashevts.PullRequestReviewer.dto.action.Action;
import com.eashevts.PullRequestReviewer.service.ResourcesReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class ActionLoader {
    ResourcesReader resourcesReader;
    private final String actionDirectory;

    final ObjectMapper mapper = new YAMLMapper();
    List<Action> actions;

    public ActionLoader(ResourcesReader resourcesReader, FilesConfiguration configLoader){
        this.resourcesReader = resourcesReader;
        actionDirectory = configLoader.getActionDirectory();
        actions = loadAction();
    }

    @SneakyThrows
    public List<Action> loadAction(){
        Map<String, String> actionResources = resourcesReader.readResources(actionDirectory);
        List<Action> actionList = new ArrayList<>();
        actionResources.values().forEach(
                v -> {
                    try {
                        actionList.add(mapper.readValue(v, Action.class));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return actionList;
    }

}
