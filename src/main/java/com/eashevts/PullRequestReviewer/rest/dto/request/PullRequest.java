package com.eashevts.PullRequestReviewer.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PullRequest{
    public int id;
    public int version;
    public String title;
    public String state;
    @JsonProperty("open")
    public boolean isOpen;
    public boolean closed;
    public long createdDate;
    public long updatedDate;
    public FromRef fromRef;
    public ToRef toRef;
    public boolean locked;
    public ReviewUser author;
    public List<ReviewUser> reviewers;
    public List<ReviewUser> participants;
}
