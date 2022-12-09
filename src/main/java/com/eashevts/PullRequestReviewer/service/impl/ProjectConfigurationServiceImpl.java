package com.eashevts.PullRequestReviewer.service.impl;


import com.eashevts.PullRequestReviewer.configuration.action.ActionLoader;
import com.eashevts.PullRequestReviewer.configuration.project.ProjectLoader;
import com.eashevts.PullRequestReviewer.dto.ProjectConfiguration;
import com.eashevts.PullRequestReviewer.dto.RepositoryEvent;
import com.eashevts.PullRequestReviewer.service.ProjectConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProjectConfigurationServiceImpl implements ProjectConfigurationService {
    ActionLoader actionLoader;
    ProjectLoader projectLoader;


    @Override
    public RepositoryEvent getRepositoryConfig(Long repositoryId, String inputType) {
        return getProjectConfig(repositoryId).getRepositoryEvent()
                .stream().filter(v -> Objects.equals(v.getName(), inputType))
                .findFirst().get();
    }

    @Override
    public ProjectConfiguration getProjectConfig(Long repositoryId) {
        return getProjectsConfig().stream().filter(v -> v.getRepositoryID() == repositoryId)
                .findFirst()
                .get();
    }

    private List<ProjectConfiguration> getProjectsConfig() {
        return projectLoader.loadProjects();
    }

}
