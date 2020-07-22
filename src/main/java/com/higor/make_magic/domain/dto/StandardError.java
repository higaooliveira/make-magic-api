package com.higor.make_magic.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.HashSet;

/**
 * This class represents the returned standard Structure in Exception case
 */
public class StandardError {

    private HashSet<String> messages = new HashSet();
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;

    public StandardError(String path, Instant date) {
        this.path = path;
        this.date = date;
    }

    public HashSet<String> getMessages() {return this.messages;}

    public void setMessages(String message) {this.messages.add(message);}

    public String getPath() {return this.path;}

    public void setPath(String path) {this.path = path;}

    public Instant getDate() {return this.date;}

    public void setDate(Instant date) {this.date = date;}
}
