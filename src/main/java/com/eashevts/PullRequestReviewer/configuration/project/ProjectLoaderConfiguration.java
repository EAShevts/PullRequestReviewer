package com.eashevts.PullRequestReviewer.configuration.project;

import com.eashevts.PullRequestReviewer.configuration.FilesConfiguration;
import com.eashevts.PullRequestReviewer.service.ResourcesReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectLoaderConfiguration {
    @Bean
    public ProjectLoader projectLoader(ResourcesReader resourcesReader, FilesConfiguration configLoader) {
        return new ProjectLoader(resourcesReader, configLoader);
    }
}
