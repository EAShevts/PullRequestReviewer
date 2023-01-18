package com.eashevts.PullRequestReviewer.service;

import java.util.Map;

public interface TemplateService {
    public String processTemplateBodyAction(Map<String, Object> parameters, String actionName);
    public String processTemplateURL(Map<String, Object> parameters, String actionName);
}
