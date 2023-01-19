package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.service.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DataServiceImpl implements DataService {
    private static String EMPTY_VALUE = "";
    ObjectMapper mapper = new ObjectMapper();

    public String getValueByJsonPath(Object value, String jsonPath) {
        try {
            return JsonPath.parse(convertToJson(value)).read(jsonPath, String.class);
        } catch (PathNotFoundException ex) {
            log.info("Not found value from json path {} in object", jsonPath, value);
            return EMPTY_VALUE;
        }
    }

    public String getValue(Object value, String jsonPath) {
        return skipParameterBody(getValueByJsonPath(value, jsonPath));
    }


    public JsonNode getParameterFromValue(Object value, String jsonPath) {
        try {
            return mapper.readTree(getParameterBody(getValueByJsonPath(value, jsonPath)));
        } catch (JsonProcessingException e) {
            return mapper.createObjectNode();
        }
    }

    @SneakyThrows
    private String convertToJson(Object value) {
        return mapper.writeValueAsString(value);
    }

    @SneakyThrows
    public JsonNode convertToJsonNode(Object value) {
        try {
            return mapper.readTree(String.valueOf(value));
        } catch (JsonProcessingException e) {
            return mapper.createObjectNode();
        }
    }

    private String skipParameterBody(String value) {
        return value.contains("{") ? value.substring(0, value.indexOf("{")) : value;
    }

    public String getParameterBody(String value) {
        return value.contains("{") ? value.substring(value.indexOf("{")) : value;
    }
}
