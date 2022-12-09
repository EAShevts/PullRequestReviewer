package com.eashevts.PullRequestReviewer.rest.controller;

import com.eashevts.PullRequestReviewer.rest.dto.request.SourceRequest;
import com.eashevts.PullRequestReviewer.service.ExecuteService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    private final ExecuteService executeService;
    private final ObjectMapper mapper = new ObjectMapper();

//    @PostMapping(path = "process", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseBody processEvent(@RequestBody SourceRequest sourceRequest){
//
//        return null;
//    }

    @PostMapping(path = "process", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody processEvent(@RequestParam String inputType,
                                     @RequestParam Long repositoryId, @RequestBody JsonNode jsonValue) {
        try {
            SourceRequest sourceRequest = mapper.convertValue(jsonValue, SourceRequest.class);
            executeService.processEvent(sourceRequest, inputType, repositoryId);
        } catch (IllegalArgumentException e) {

        }

        return null;
    }
}
