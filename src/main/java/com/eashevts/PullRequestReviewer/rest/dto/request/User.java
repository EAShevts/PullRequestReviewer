package com.eashevts.PullRequestReviewer.rest.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    String emailAddress;
    Long id;
    String displayName;
    boolean active;
    String slug;
    String type;


}
