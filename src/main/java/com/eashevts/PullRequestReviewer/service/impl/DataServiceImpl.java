package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.rest.dto.request.SourceRequest;
import com.eashevts.PullRequestReviewer.service.DataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {
    ObjectMapper mapper = new ObjectMapper();


    public String getValueByJsonPath(Object value, String jsonPath){
        return JsonPath.parse(convertToJson(value)).read(jsonPath);
    }
    @SneakyThrows
    private String convertToJson(Object value){
            return mapper.writeValueAsString(value);
    }
}
