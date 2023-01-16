package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RequestBookModel {
    private String title;
    private String author;
}