package com.eashevts.PullRequestReviewer.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToRef {
    public String id;
    public String displayId;
    public String latestCommit;
    public Repository repository;
}
