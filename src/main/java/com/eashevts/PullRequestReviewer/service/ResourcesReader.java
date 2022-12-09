package com.eashevts.PullRequestReviewer.service;

import java.io.IOException;
import java.util.Map;

public interface ResourcesReader {

    Map<String, String> readResources(String pattern) throws IOException;

//    Map<String, Object> readResources(String pattern, Class clazz) throws IOException;
}
