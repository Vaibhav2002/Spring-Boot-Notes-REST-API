package com.vaibhav.notes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "collection_table")
@Table(name = "collection_table")
public class Collection {
    @SequenceGenerator(
            name = "collection_sequence",
            sequenceName = "c_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collection_sequence"
    )
    @Id
    long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("isImportant")
    boolean important;

    @JsonProperty("userId")
    long userId;

    @JsonProperty("timeStamp")
    String timeStamp;

    public Collection(long id, String name, boolean important, long userId, String timeStamp) {
        this.id = id;
        this.name = name;
        this.important = important;
        this.userId = userId;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Collection(String name, boolean important, Long userId, String timeStamp) {
        this.name = name;
        this.important = important;
        this.userId = userId;
        this.timeStamp = timeStamp;
    }

}
