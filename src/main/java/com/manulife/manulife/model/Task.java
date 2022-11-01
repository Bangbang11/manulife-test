package com.manulife.manulife.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "task")
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private String id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "status")
    @JsonProperty("status")
    private Boolean status;

    public Task(String id, String name, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
