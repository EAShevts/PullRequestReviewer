package com.eashevts.PullRequestReviewer.service;

import java.util.Map;

public interface TemplateService {
    public String processTemplate(Map<String, Object> parameters, String actionName);
}
