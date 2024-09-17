package org.example.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
    //@JsonProperty("title")
    private String title;

    //@JsonProperty("description")
    private String description;
}
