package com.optimas.componentservice.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int errorCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int errorCode, String message, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
