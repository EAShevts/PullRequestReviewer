package com.eashevts.PullRequestReviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.List;

@Value
public class Rule {
    private List<Condition> conditions;
    private String action;

    private int priority;

    private String savedName;

    public Rule(@JsonProperty("action") String action,
                @JsonProperty("conditions") List<Condition> conditions,

                @Nullable
                 @JsonProperty("priority")
                 Integer priority,

                @Nullable
                 @JsonProperty("savedAs")
                 String savedName)
    {
        this.conditions = conditions;
        this.action = action;
        this.priority = priority != null ? priority : 0;
        this.savedName = savedName;
    }
}
