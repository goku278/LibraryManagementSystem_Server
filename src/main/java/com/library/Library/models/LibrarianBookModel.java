package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LibrarianBookModel {
    private Long libraryId;
    private Long bookId;
    private String bookTitle;
    private String isbn;
    private String bookType;
    private String bookAuthor;
    private long pageNo;
    private String synopsis;
    private double price;
}
