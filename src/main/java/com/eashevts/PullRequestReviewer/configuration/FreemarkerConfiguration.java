package com.eashevts.PullRequestReviewer.configuration;

import freemarker.cache.StringTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreemarkerConfiguration {

    @Bean
    public StringTemplateLoader stringTemplateLoader() {
        return new StringTemplateLoader();
    }

    @Bean
    public freemarker.template.Configuration templateProcessorConfiguration(StringTemplateLoader stringTemplateLoader) {

        freemarker.template.Configuration configuration =
                new freemarker.template.Configuration();
        configuration.setBooleanFormat("true,false");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(stringTemplateLoader);
        configuration.setNumberFormat("computer"); //disable human formatting for numbers
        return configuration;
    }
}
