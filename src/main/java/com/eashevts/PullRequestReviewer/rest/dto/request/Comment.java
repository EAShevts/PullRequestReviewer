package com.eashevts.PullRequestReviewer.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    public Properties properties;
    public int id;
    public int version;
    public String text;
    public User author;
    public long createdDate;
    public long updatedDate;
    public List<Comment> comments;
    public List<Object> tasks;

    public class Properties{
        public int repositoryId;
    }
}
