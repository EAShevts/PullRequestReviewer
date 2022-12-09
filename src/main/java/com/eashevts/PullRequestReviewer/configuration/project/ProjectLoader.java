package com.eashevts.PullRequestReviewer.configuration.project;


import com.eashevts.PullRequestReviewer.configuration.FilesConfiguration;
import com.eashevts.PullRequestReviewer.dto.ProjectConfiguration;
import com.eashevts.PullRequestReviewer.service.ResourcesReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProjectLoader {
    ResourcesReader resourcesReader;
    private final String projectDirectory;

    final ObjectMapper mapper = new YAMLMapper();

    public ProjectLoader(ResourcesReader resourcesReader, FilesConfiguration configLoader) {
        this.resourcesReader = resourcesReader;
        projectDirectory = configLoader.ProjectDirectory;
    }

    @SneakyThrows
    public List<ProjectConfiguration> loadProjects() {
        Map<String, String> projectResources = resourcesReader.readResources(projectDirectory);
        return projectResources.values().stream()
                .map(v -> {
                    try {
                        return mapper.readValue(v, ProjectConfiguration.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }


}
