package com.library.Library.models;
import lombok.Data;

import java.util.*;
@Data
public class StatusMessage {
    String code;
    String message;

    @Override
    public String toString() {
        return "StatusMessage{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}