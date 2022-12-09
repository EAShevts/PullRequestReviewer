package com.eashevts.PullRequestReviewer.rest.dto.request;


import com.eashevts.PullRequestReviewer.utils.ZoneDateJsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SourceRequest {
    String eventKey;
    @JsonDeserialize(using = ZoneDateJsonDeserialize.class)
    ZonedDateTime date;
    User actor;
    PullRequest pullRequest;
    Comment comment;
    Long commentParentId;

    public int getRepositoryId(){
        return pullRequest.getFromRef().getRepository().id;
    }
}
