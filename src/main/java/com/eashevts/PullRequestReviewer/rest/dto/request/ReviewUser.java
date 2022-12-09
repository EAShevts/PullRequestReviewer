package com.eashevts.PullRequestReviewer.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUser {
    public User user;
    public String role;
    public boolean approved;
    public String status;
}
