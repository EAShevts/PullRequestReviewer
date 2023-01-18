package com.eashevts.PullRequestReviewer.configuration;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfiguration {

    @Bean
    public StringTemplateLoader stringTemplateLoader() {
        return new StringTemplateLoader();
    }

    @Bean
    public Configuration templateProcessorConfiguration(StringTemplateLoader stringTemplateLoader) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setBooleanFormat("true,false");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(stringTemplateLoader);
        configuration.setNumberFormat("computer"); //disable human formatting for numbers
        return configuration;
    }
}
