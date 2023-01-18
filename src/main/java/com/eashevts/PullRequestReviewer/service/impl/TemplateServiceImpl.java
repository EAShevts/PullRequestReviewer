package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.configuration.action.ActionLoader;
import com.eashevts.PullRequestReviewer.service.TemplateService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    StringTemplateLoader stringTemplateLoader;
    freemarker.template.Configuration templateProcessorConfiguration;

    private final String POSTFIX = "Template";
    private final String POSTFIX_URL = "URL";

    @Autowired
    public TemplateServiceImpl(StringTemplateLoader stringTemplateLoader,
                               Configuration templateProcessorConfiguration, ActionLoader actionLoader) {
        this.stringTemplateLoader = stringTemplateLoader;
        this.templateProcessorConfiguration = templateProcessorConfiguration;
        actionLoader.loadAction().forEach(
                action ->
                {
                    stringTemplateLoader.putTemplate(action.getName() + POSTFIX, action.getBody());
                    stringTemplateLoader.putTemplate(action.getName() + POSTFIX_URL + POSTFIX, action.getUrl());
                }

        );
    }

    @SneakyThrows
    public String processTemplateBodyAction(Map<String, Object> parameters, String actionName) {
        Template template = templateProcessorConfiguration.getTemplate(actionName + POSTFIX);
        return generateString(template, parameters);
    }
    @SneakyThrows
    public String processTemplateURL(Map<String, Object> parameters, String actionName) {
        Template template = templateProcessorConfiguration.getTemplate(actionName + POSTFIX_URL + POSTFIX);
        return generateString(template, parameters).strip();
    }

    private String generateString(Template template, Map<String, Object> params) throws IOException, TemplateException {
        try (StringWriter stringWriter = new StringWriter()) {
            template.process(params, stringWriter);
            return stringWriter.toString();
        }
    }
}
