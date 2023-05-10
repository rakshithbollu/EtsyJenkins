package com.example.EtsyProject.EtsyProject.entity;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorModel {
    private int status;

    private LocalDateTime timestamp;

    private String message;

    private String details;

    public ErrorModel(int status, String message, String details) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
