package com.eashevts.PullRequestReviewer.configuration;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "configuration")
@Getter
@Setter
public class FilesConfiguration {

    @NotNull
    String actionDirectory;

    @NotNull
    public
    String ProjectDirectory;

}
