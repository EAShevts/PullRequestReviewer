package com.eashevts.PullRequestReviewer.service;

import com.eashevts.PullRequestReviewer.dto.ProjectConfiguration;
import com.eashevts.PullRequestReviewer.dto.RepositoryEvent;

public interface ProjectConfigurationService {

    RepositoryEvent getRepositoryConfig(Long repositoryId, String inputType);

    ProjectConfiguration getProjectConfig(Long repositoryId);
}
