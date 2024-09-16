package org.example.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    // Constructor, getters, and setters
    public Message() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

