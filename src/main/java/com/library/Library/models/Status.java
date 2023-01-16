package com.library.Library.models;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Status {
    int code;
    String message;
    Long userId;
    String userRole;
    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public Status(int code, String message, Long userId) {
        this.code = code;
        this.message = message;
        this.userId = userId;
    }

    public Status(int code, String message, Long userId, String userRole) {
        this.code = code;
        this.message = message;
        this.userId = userId;
        this.userRole = userRole;
    }
    public Status() {
    }

}