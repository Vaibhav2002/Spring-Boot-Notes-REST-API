package com.vaibhav.notes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity(name = "note")
@Table(name = "note")
public class Note {


    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "no_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    @Id
    Long id;
    @JsonProperty("collectionId")
    Long collectionId;
    @JsonProperty("text")
    String text;
    @JsonProperty("isImportant")
    Boolean important;
    @JsonProperty("timeStamp")
    String timeStamp;


    public Note(Long id, Long collectionId, String text, Boolean important, String timeStamp) {
        this.id = id;
        this.collectionId = collectionId;
        this.text = text;
        this.important = important;
        this.timeStamp = timeStamp;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }


    public Note() {
    }

    public Note(Long collectionId, String text, String timeStamp, boolean important) {
        this.collectionId = collectionId;
        this.text = text;
        this.timeStamp = timeStamp;
        this.important = important;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


}
