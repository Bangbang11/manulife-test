package com.manulife.manulife.mapper.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manulife.manulife.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private Boolean status;

    public TaskResponse(Task task) {
        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        status = task.getStatus();
    }
}
