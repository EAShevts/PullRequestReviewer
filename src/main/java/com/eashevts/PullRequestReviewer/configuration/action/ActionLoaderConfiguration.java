package com.eashevts.PullRequestReviewer.configuration.action;


import com.eashevts.PullRequestReviewer.configuration.FilesConfiguration;
import com.eashevts.PullRequestReviewer.service.ResourcesReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionLoaderConfiguration {

    @Bean
    public ActionLoader actionLoader(ResourcesReader resourcesReader, FilesConfiguration configLoader) {
        return new ActionLoader(resourcesReader, configLoader);
    }
}
