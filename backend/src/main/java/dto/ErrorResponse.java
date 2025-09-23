package dto;

import java.time.Instant;

public class ErrorResponse {
    private String message;
    private int code;
    private String timestamp;

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
        this.timestamp = Instant.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

